package com.example.app_replica_friendfindr.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Person (
    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo(name = "first_name")
    val firstName: String?,

    @ColumnInfo(name = "last_name")
    val lastName: String?,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "cell")
    val cell: String?,

    @ColumnInfo(name = "picture")
    val picture: String?,
)