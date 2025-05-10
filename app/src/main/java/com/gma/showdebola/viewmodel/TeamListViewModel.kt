package com.gma.showdebola.viewmodel

import androidx.lifecycle.ViewModel
import com.gma.showdebola.viewmodel.state.UiState
import kotlinx.coroutines.flow.StateFlow

abstract class TeamListViewModel : ViewModel() {
    abstract val uiTeamListState: StateFlow<UiState>

    abstract fun searchTeams(filter: String? = null)

}