package com.gma.data_remote.datasourceImplementation

import android.util.Log
import com.gma.data_remote.const.PLAYERS
import com.gma.data_remote.const.TEAMS
import com.gma.data_remote.const.TEAM_ID
import com.gma.data_remote.datasource.PlayersDataSource
import com.gma.data_remote.mapper.toObjectList
import com.gma.data_remote.model.DatabasePlayer
import com.gma.data_remote.network.FirebaseDatabase

class PlayersDataSourceImpl(
    private val database: FirebaseDatabase
) : PlayersDataSource {
    override suspend fun update(player: DatabasePlayer): DatabasePlayer {
        database.reference.child(PLAYERS).child(player.id).setValue(player)
        return player
    }

    override suspend fun playerListListener(
        teamId: String,
        successListener: (List<DatabasePlayer>?) -> Unit
    ) {
        database.reference.child(PLAYERS)
            .orderByChild(TEAM_ID)
            .equalTo(teamId)
            .get()
            .addOnSuccessListener { snapshot ->
                val teamList = snapshot.toObjectList(DatabasePlayer::class)
                successListener(teamList)
            }
            .addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
                successListener(null)
            }
    }

}