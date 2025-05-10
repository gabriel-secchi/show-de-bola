package com.gma.showdebola.config

import com.gma.showdebola.viewmodel.TeamListViewModel
import com.gma.showdebola.viewmodel.TeamListViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    viewModel<TeamListViewModel> {
        TeamListViewModelImpl(
            teamUseCase = get()
        )
    }

}