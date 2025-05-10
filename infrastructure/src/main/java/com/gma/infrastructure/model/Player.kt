package com.gma.infrastructure.model

data class Player(
    val id: String,
    val teamId: String,
    val name: String,
    val mainPosition: String,
    val ability: Int
)
