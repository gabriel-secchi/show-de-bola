package com.gma.data_remote.datasource

import com.gma.data_remote.model.DatabasePlayer

interface PlayersDataSource {

    suspend fun update(player: DatabasePlayer): DatabasePlayer
    suspend fun playerListListener(teamId: String, successListener: (List<DatabasePlayer>?) -> Unit)

}