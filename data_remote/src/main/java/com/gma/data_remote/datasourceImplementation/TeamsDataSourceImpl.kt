package com.gma.data_remote.datasourceImplementation

import android.util.Log
import com.gma.data_remote.const.TEAMS
import com.gma.data_remote.datasource.TeamsDataSource
import com.gma.data_remote.mapper.toObjectList
import com.gma.data_remote.model.DatabaseTeam
import com.gma.data_remote.network.FirebaseDatabase

class TeamsDataSourceImpl(
    private val database: FirebaseDatabase
) : TeamsDataSource {
    override suspend fun update(team: DatabaseTeam): DatabaseTeam {
        database.reference.child(TEAMS).child(team.id).setValue(team)
        return team
    }

    override suspend fun teamListListener(successListener: (List<DatabaseTeam>?) -> Unit) {
        database.reference.child(TEAMS).get()
            .addOnSuccessListener { snapshot ->
                val teamList = snapshot.toObjectList(DatabaseTeam::class)
                successListener(teamList)
            }
            .addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
                successListener(null)
            }
    }
}