package com.gma.showdebola.viewmodel.implementation

import androidx.core.text.isDigitsOnly
import com.gma.infrastructure.model.Game
import com.gma.infrastructure.model.Player
import com.gma.showdebola.BuildConfig
import com.gma.showdebola.viewmodel.GameBuilderUseCase
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content

class GameBuilderUseCaseImpl : GameBuilderUseCase() {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apiKey
    )

    override suspend fun buildGame(players: List<Player>): Game {
        val teamNumbers = buildTeamNumbers(players)
        val game = teamNumbers.toGameTeams(players)
        return game
    }

    private suspend fun buildTeamNumbers(players: List<Player>): List<String> {
        val contentFirstTry = aiGenerateContent(buildPrompt(players))
        var teamNumbers = splitAiResultIntoTeamsNumbers(contentFirstTry)
        if (teamNumbers.isEmpty()) {
            val contentSecondTry = aiGenerateContent(retryPrompt(contentFirstTry))
            teamNumbers = splitAiResultIntoTeamsNumbers(contentSecondTry)
        }
        return teamNumbers
    }


    private suspend fun aiGenerateContent(prompt: String): String? {
        val response = generativeModel.generateContent(
            content {
                text(prompt)
            }
        )

        return response.text
    }

    private fun splitAiResultIntoTeamsNumbers(text: String?): List<String> {
        if (text.isNullOrEmpty())
            return listOf()

        val regex = Regex(EXPRESSION)
        val result = regex.findAll(text)
            .map { it.groupValues[1] }
            .toList()

        return result
    }

    private fun List<String>.toGameTeams(players: List<Player>): Game {
        val gameTeams = this.map {
            val listNumbers = it.split(",")
            val listTeamPlayers = mutableListOf<Player>()
            listNumbers.forEach { numberText ->
                val trimmedNumberText = numberText.trim()
                if(trimmedNumberText.isDigitsOnly()){
                    val number = numberText.trim().toInt()
                    listTeamPlayers.add(
                        players[(number -1)]
                    )
                }
            }
            listTeamPlayers
        }

        val game = Game(
            firstTeam = gameTeams.first(),
            secondTeam = gameTeams.last(),
        )

        return game
    }

    private fun buildPrompt(playersInGame: List<Player>): String {
        var prompt = "Tenho uma lista de jogadores de futebol numerados " +
                "de 1 a ${playersInGame.size + 1}  \n" +
                "Cada jogador possui dois atributos:\n" +
                "-posição (ex: goleiro, zaga, ataque, meio, LD, LE)\n" +
                "-habilidade (número de 0 a 10)\n" +
                "Preciso que  você divida esses jogadores em dois times o mais equilibrado possível, " +
                "com as seguintes regras:\n" +
                "1. A quantidade de jogadores em cada time deve ser igual ou com no máximo 1 jogadorde diferença.\n" +
                "2. A soma das habilidades dos times deve ser o mais parecida possível.\n" +
                "3. Os jogadores com maior habilidade devem ser separados entre os dois times.\n" +
                "4. Os times devem ter equilíbrio por posição, especialmente garantindo que cada " +
                "time tenha ao menos um goleiro.\n\n" +
                "***O mais importante é que o resultado final seja no formato de dois arrays, " +
                "contendo os números dos jogadores de cada time, apenas isso deveser retornado. " +
                "Nada de explicações, só os dois arrays no formato:***\n" +
                "[números dos jogadores do time A], [números dos jogadores do time B]" +
                "Aqui está a lista de jogadores:\n"

        playersInGame.forEachIndexed { index, player ->
            prompt = prompt
                .plus("${index + 1} - ")
                .plus("${player.mainPosition} - ")
                .plus("${player.ability} \n")
        }
        return prompt
    }

    private fun retryPrompt(text: String?): String {
        return "gere dois arrays numéricos nesse formato [2,3,7,9],[1,4,5,8] " +
                "com os números dos jogadores sugeridos no testo abaixo:\n\n " +
                (text ?: "")
    }

    private companion object {
        private const val EXPRESSION = "\\[([0-9 ,]*?)\\]"

    }

}