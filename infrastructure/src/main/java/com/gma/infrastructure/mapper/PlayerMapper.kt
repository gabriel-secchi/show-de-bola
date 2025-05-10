package com.gma.infrastructure.mapper

import com.gma.data_remote.model.DatabasePlayer
import com.gma.data_remote.model.DatabaseTeam
import com.gma.infrastructure.model.Player
import com.gma.infrastructure.model.Team

fun Player.toDatabasePlayer(): DatabasePlayer {
    return DatabasePlayer(
        id = this.id,
        teamId = this.teamId,
        name = this.name,
        mainPosition = this.mainPosition,
        ability = this.ability
    )
}

fun DatabasePlayer.toLocalObject(): Player {
    return Player(
        id = this.id,
        teamId = this.teamId,
        name = this.name,
        mainPosition = this.mainPosition,
        ability = this.ability
    )
}

fun List<DatabasePlayer>.toListLocalObject(): List<Player> {
    return this.map { databaseObject ->
        databaseObject.toLocalObject()
    }
}