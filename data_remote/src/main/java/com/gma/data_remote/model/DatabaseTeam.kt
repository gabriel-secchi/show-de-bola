package com.gma.data_remote.model

import java.util.UUID

data class DatabaseTeam(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val time: String
)
