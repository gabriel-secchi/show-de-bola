package com.gma.data_remote.config

import com.gma.data_remote.datasource.PlayersDataSource
import com.gma.data_remote.datasource.TeamsDataSource
import com.gma.data_remote.datasourceImplementation.PlayersDataSourceImpl
import com.gma.data_remote.datasourceImplementation.TeamsDataSourceImpl
import com.gma.data_remote.network.FirebaseDatabase
import org.koin.dsl.module

val dataRemotemodule = module {

    single {
        FirebaseDatabase()
    }

    factory<TeamsDataSource> {
        TeamsDataSourceImpl(
            database = get()
        )
    }

    factory<PlayersDataSource> {
        PlayersDataSourceImpl(
            database = get()
        )
    }

}