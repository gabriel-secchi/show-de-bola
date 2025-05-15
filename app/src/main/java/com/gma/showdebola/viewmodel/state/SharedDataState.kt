package com.gma.showdebola.viewmodel.state

import com.gma.infrastructure.model.Game
import com.gma.infrastructure.model.Player
import com.gma.infrastructure.model.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedDataState {
    private val _selectedTeam: MutableStateFlow<Team?> = MutableStateFlow(null)
    val selectedTeam: StateFlow<Team?> = _selectedTeam.asStateFlow()

    private val _selectedPlayer: MutableStateFlow<Player?> = MutableStateFlow(null)
    val selectedPlayer: StateFlow<Player?> = _selectedPlayer.asStateFlow()

    private val _gameTeams: MutableStateFlow<Game?> = MutableStateFlow(null)
    val gameTeams: StateFlow<Game?> = _gameTeams.asStateFlow()

    fun updateSelectedTeam(team: Team?) {
        _selectedTeam.value = team
    }

    fun updateSelectedPlayer(player: Player?) {
        _selectedPlayer.value = player
    }

    fun updateGameTeams(game: Game?) {
        _gameTeams.value = game
    }
}