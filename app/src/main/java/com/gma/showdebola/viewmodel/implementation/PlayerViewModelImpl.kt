package com.gma.showdebola.viewmodel.implementation

import androidx.lifecycle.viewModelScope
import com.gma.infrastructure.model.Player
import com.gma.infrastructure.useCase.PlayerUseCase
import com.gma.showdebola.viewmodel.GameBuilderUseCase
import com.gma.showdebola.viewmodel.PlayerParams
import com.gma.showdebola.viewmodel.PlayerViewModel
import com.gma.showdebola.viewmodel.state.PlayerInGameState
import com.gma.showdebola.viewmodel.state.PlayerInGameState.AddPlayer
import com.gma.showdebola.viewmodel.state.PlayerInGameState.RemovePlayer
import com.gma.showdebola.viewmodel.state.SharedDataState
import com.gma.showdebola.viewmodel.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerViewModelImpl(
    val playerUseCase: PlayerUseCase,
    val sharedDataState: SharedDataState,
    val gameBuilder: GameBuilderUseCase
) : PlayerViewModel() {
    private val _uiPlayerListState: MutableStateFlow<UiState<List<Player>?>> =
        MutableStateFlow(UiState.Initial)
    override val uiPlayerListState: StateFlow<UiState<List<Player>?>> =
        _uiPlayerListState.asStateFlow()

    private val _uiPlayersInGameState: MutableStateFlow<MutableList<Player>> =
        MutableStateFlow(mutableListOf())
    override val uiPlayersInGameState: StateFlow<List<Player>> = _uiPlayersInGameState.asStateFlow()

    override fun searchPlayers(teamId: String) {
        _uiPlayerListState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                playerUseCase.get(teamId) { teamList ->
                    val sortedTeamList = teamList?.sortedBy { it.name }
                    _uiPlayerListState.value = UiState.Success(sortedTeamList)
                }
            } catch (ex: Exception) {
                _uiPlayerListState.value = UiState.Error(ex.message ?: "")
            }
        }
    }

    override fun updatePlayer(params: PlayerParams) {
        var player = Player(
            teamId = sharedDataState.selectedTeam.value?.id ?: "",
            name = params.name,
            mainPosition = params.position,
            ability = params.ability
        )
        params.playerEdit?.let {
            player = player.copy(
                id = it.id
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            playerUseCase.update(player)
            _uiPlayerListState.value = UiState.Success(null)
        }
    }

    override fun playersInGame(statePlayer: PlayerInGameState) {
        when (statePlayer) {
            is AddPlayer -> {
                _uiPlayersInGameState.value = _uiPlayersInGameState.value.toMutableList().apply {
                    add(statePlayer.player)
                }
            }

            is RemovePlayer -> {
                _uiPlayersInGameState.value = _uiPlayersInGameState.value.toMutableList().apply {
                    remove(statePlayer.player)
                }
            }
        }
    }

    override fun sorterTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = gameBuilder.buildGame( _uiPlayersInGameState.value )
            sharedDataState.updateGameTeams(result)
        }
    }


}