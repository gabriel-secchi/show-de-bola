package com.gma.showdebola.viewmodel.implementation

import androidx.lifecycle.viewModelScope
import com.gma.infrastructure.model.Team
import com.gma.infrastructure.useCase.TeamUseCase
import com.gma.showdebola.viewmodel.TeamViewModel
import com.gma.showdebola.viewmodel.state.SharedDataState
import com.gma.showdebola.viewmodel.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeamViewModelImpl(
    val teamUseCase: TeamUseCase,
    val sharedDataState: SharedDataState
) : TeamViewModel() {
    private val _uiTeamListState: MutableStateFlow<UiState<List<Team>?>> = MutableStateFlow(UiState.Initial)
    override val uiTeamListState: StateFlow<UiState<List<Team>?>> = _uiTeamListState.asStateFlow()

    override fun searchTeams(filter: String?) {
        _uiTeamListState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                teamUseCase.get { teamList ->
                    _uiTeamListState.value = UiState.Success(teamList)
                }
            } catch (ex: Exception) {
                _uiTeamListState.value = UiState.Error(ex.message ?: "")
            }
        }
    }

    override fun updateTeam(id: String?, name: String, time: String) {
        val team = if (id.isNullOrEmpty()) {
            Team(
                name = name,
                time = time
            )
        } else {
            Team(
                id = id,
                name = name,
                time = time
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            val updatedTeam = teamUseCase.update(team)
            sharedDataState.updateSelectedTeam(updatedTeam)
        }
    }
}