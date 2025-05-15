package com.gma.showdebola.viewmodel

import com.gma.infrastructure.model.Game
import com.gma.infrastructure.model.Player

abstract class GameBuilderUseCase {
    abstract suspend fun buildGame(players: List<Player>): Game
}