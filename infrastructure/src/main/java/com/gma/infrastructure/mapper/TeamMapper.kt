package com.gma.infrastructure.mapper

import com.gma.data_remote.model.DatabaseTeam
import com.gma.infrastructure.model.Team

fun Team.toDatabaseTeam(): DatabaseTeam {
    return DatabaseTeam(
        id = this.id,
        name = this.name,
        time = this.time
    )
}

fun DatabaseTeam.toLocalObject(): Team {
    return Team(
        id = this.id,
        name = this.name,
        time = this.time
    )
}

fun List<DatabaseTeam>.toListLocalObject(): List<Team> {
    return this.map { databaseObject ->
        databaseObject.toLocalObject()
    }
}