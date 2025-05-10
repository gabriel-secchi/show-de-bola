package com.gma.showdebola.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gma.infrastructure.useCase.TeamUseCase
import com.gma.showdebola.viewmodel.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeamListViewModelImpl(
    val teamUseCase: TeamUseCase
): TeamListViewModel() {
    private val _uiTeamListState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    override val uiTeamListState: StateFlow<UiState> = _uiTeamListState.asStateFlow()

    init {
        searchTeams()
    }

    override fun searchTeams(filter: String?) {
        _uiTeamListState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                teamUseCase.get { teamList ->
                    _uiTeamListState.value = UiState.Success(teamList)
                }
            }
            catch (ex: Exception) {
                _uiTeamListState.value = UiState.Error(ex.message ?: "")
            }
        }
    }
}