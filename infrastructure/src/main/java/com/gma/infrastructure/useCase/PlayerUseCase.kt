package com.gma.infrastructure.useCase

import com.gma.infrastructure.model.Player

interface PlayerUseCase {

    suspend fun update(player: Player): Player
    suspend fun get(teamId: String, successListener: (List<Player>?) -> Unit)

}