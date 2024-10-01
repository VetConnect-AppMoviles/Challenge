package com.example.app_project_challenge.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "front_default")
    val sprite: String?,

    @ColumnInfo(name = "order")
    val order: Int?
)