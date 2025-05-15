package com.gma.infrastructure.model

import java.util.UUID

data class Team(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val time: String
)
