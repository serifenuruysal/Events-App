package com.androidapp.eventsapp.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "guest",
    indices = [Index(value = ["id"], unique = true)]
)
data class Guest(
    val barcode: String?,
    val blog: String?,
    val cell_phone: String?,
    val company: String?,
    val created: String?,
    val email: String?,
    val event: Int,
    val first_name: String?,
    @PrimaryKey
    val id: Int,
    val job_title: String?,
    val last_name: String?,
    val modified: String?,
    val nfc_tag: String??,
    val objstatus: String?,
    val phone: String?,
    val prefix: String?,
    val registration_type: Int,
    val selfie: String?,
    val source: String?,
    val suffix: String?,
    val website: String?,
    val work_phone: String?
)