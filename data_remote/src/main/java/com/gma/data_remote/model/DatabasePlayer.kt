package com.gma.data_remote.model

import java.util.UUID

data class DatabasePlayer(
    val id: String = UUID.randomUUID().toString(),
    val teamId: String,
    val name: String,
    val mainPosition: String,
    val ability: Int
)
