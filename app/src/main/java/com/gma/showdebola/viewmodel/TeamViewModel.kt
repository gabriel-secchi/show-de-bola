package com.gma.showdebola.viewmodel

import androidx.lifecycle.ViewModel
import com.gma.infrastructure.model.Team
import com.gma.showdebola.viewmodel.state.UiState
import kotlinx.coroutines.flow.StateFlow

abstract class TeamViewModel : ViewModel() {
    abstract val uiTeamListState: StateFlow<UiState<List<Team>?>>

    abstract fun searchTeams(filter: String? = null)
    abstract fun updateTeam(id: String?, name: String, time: String)
}