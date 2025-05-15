package com.gma.showdebola.viewmodel.state

import com.gma.infrastructure.model.Player

sealed interface PlayerInGameState {

    data class AddPlayer(val player: Player) : PlayerInGameState
    data class RemovePlayer(val player: Player) : PlayerInGameState

}