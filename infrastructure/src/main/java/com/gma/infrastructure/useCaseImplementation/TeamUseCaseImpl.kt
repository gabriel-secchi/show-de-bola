package com.gma.infrastructure.useCaseImplementation

import com.gma.data_remote.datasource.TeamsDataSource
import com.gma.infrastructure.mapper.toDatabaseTeam
import com.gma.infrastructure.mapper.toListLocalObject
import com.gma.infrastructure.mapper.toLocalObject
import com.gma.infrastructure.model.Team
import com.gma.infrastructure.useCase.TeamUseCase


class TeamUseCaseImpl(
    private val teamDatabase: TeamsDataSource
) : TeamUseCase {

    override suspend fun update(team: Team): Team {
        return teamDatabase.update(
            team.toDatabaseTeam()
        ).toLocalObject()
    }

    override suspend fun get(successListener: (List<Team>?) -> Unit) {
        teamDatabase.teamListListener { databaseTeamList ->
            successListener(databaseTeamList?.toListLocalObject())
        }
    }
}