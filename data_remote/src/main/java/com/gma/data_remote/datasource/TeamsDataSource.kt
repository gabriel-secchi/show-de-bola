package com.gma.data_remote.datasource

import com.gma.data_remote.model.DatabaseTeam

interface TeamsDataSource {

    suspend fun update(team: DatabaseTeam): DatabaseTeam
    suspend fun teamListListener(successListener: (List<DatabaseTeam>?) -> Unit)

}