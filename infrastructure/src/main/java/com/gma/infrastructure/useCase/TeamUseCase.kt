package com.gma.infrastructure.useCase

import com.gma.infrastructure.model.Team

interface TeamUseCase {

    suspend fun update(team: Team): Team
    suspend fun get(successListener: (List<Team>?) -> Unit)

}