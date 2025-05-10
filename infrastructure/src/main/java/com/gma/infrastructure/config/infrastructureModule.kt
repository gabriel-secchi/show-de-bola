package com.gma.infrastructure.config

import com.gma.data_remote.config.dataRemotemodule
import com.gma.infrastructure.useCase.PlayerUseCase
import com.gma.infrastructure.useCase.TeamUseCase
import com.gma.infrastructure.useCaseImplementation.PlayerUseCaseImpl
import com.gma.infrastructure.useCaseImplementation.TeamUseCaseImpl
import org.koin.dsl.module

val infrastructureModule = module {

    includes(
        dataRemotemodule
    )

    factory<TeamUseCase> {
        TeamUseCaseImpl(
            teamDatabase = get()
        )
    }

    factory<PlayerUseCase> {
        PlayerUseCaseImpl(
            playerDatabase = get()
        )
    }

}