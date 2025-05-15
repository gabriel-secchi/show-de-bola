package com.gma.showdebola.config

import com.gma.showdebola.viewmodel.GameBuilderUseCase
import com.gma.showdebola.viewmodel.PlayerViewModel
import com.gma.showdebola.viewmodel.TeamViewModel
import com.gma.showdebola.viewmodel.implementation.GameBuilderUseCaseImpl
import com.gma.showdebola.viewmodel.implementation.PlayerViewModelImpl
import com.gma.showdebola.viewmodel.implementation.TeamViewModelImpl
import com.gma.showdebola.viewmodel.state.SharedDataState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    single { SharedDataState() }

    factory<GameBuilderUseCase> {
        GameBuilderUseCaseImpl()
    }

    viewModel<TeamViewModel> {
        TeamViewModelImpl(
            teamUseCase = get(),
            sharedDataState = get()
        )
    }

    viewModel<PlayerViewModel> {
        PlayerViewModelImpl(
            playerUseCase = get(),
            sharedDataState = get(),
            gameBuilder = get()
        )
    }
}