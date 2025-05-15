package com.gma.showdebola.viewmodel

import androidx.lifecycle.ViewModel
import com.gma.infrastructure.model.Player
import com.gma.showdebola.viewmodel.state.PlayerInGameState
import com.gma.showdebola.viewmodel.state.UiState
import kotlinx.coroutines.flow.StateFlow

abstract class PlayerViewModel : ViewModel() {
    abstract val uiPlayerListState: StateFlow<UiState<List<Player>?>>
    abstract val uiPlayersInGameState: StateFlow<List<Player>>

    abstract fun searchPlayers(teamId: String)
    abstract fun updatePlayer(params: PlayerParams)
    abstract fun playersInGame(statePlayer: PlayerInGameState)
    abstract fun sorterTeams()
}

data class PlayerParams(
    val  playerEdit: Player?,
    val name: String,
    val position: String,
    val ability: Int
)