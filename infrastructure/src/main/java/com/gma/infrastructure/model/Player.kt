package com.gma.infrastructure.model

import java.util.UUID

data class Player(
    val id: String = UUID.randomUUID().toString(),
    val teamId: String,
    val name: String,
    val mainPosition: String,
    val ability: Int
)
