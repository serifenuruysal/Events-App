package com.androidapp.eventsapp.entity

data class GuestResponse(
    val count: Int,
    val end_time: String?,
    val next: String?,
    val only_checkins: Boolean,
    val page: Int,
    val previous: String?,
    val results: List<Guest>?,
    val start_time: String?,
    val timestamp: String?
)