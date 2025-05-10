package com.gma.infrastructure.useCaseImplementation

import com.gma.data_remote.datasource.PlayersDataSource
import com.gma.infrastructure.mapper.toDatabasePlayer
import com.gma.infrastructure.mapper.toListLocalObject
import com.gma.infrastructure.mapper.toLocalObject
import com.gma.infrastructure.model.Player
import com.gma.infrastructure.useCase.PlayerUseCase


class PlayerUseCaseImpl(
    private val playerDatabase: PlayersDataSource
) : PlayerUseCase {

    override suspend fun update(player: Player): Player {
        return playerDatabase.update(
            player.toDatabasePlayer()
        ).toLocalObject()
    }

    override suspend fun get(teamId: String, successListener: (List<Player>?) -> Unit) {
        playerDatabase.playerListListener(teamId) { databaseTeamList ->
            successListener(databaseTeamList?.toListLocalObject())
        }
    }
}