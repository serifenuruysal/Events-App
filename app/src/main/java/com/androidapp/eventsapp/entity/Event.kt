package com.androidapp.eventsapp.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "event",
    indices = [Index(value = ["id"], unique = true)]
)
data class Event(
    val created: String,
    val ends: String,
    val group_id: Int,
    @PrimaryKey
    val id: Int,
    val modified: String,
    val name: String,
    val starts: String,
    val timezone: String,
    val venue: String?
)