package com.androidapp.eventsapp.entity

import com.androidapp.eventsapp.entity.Event

data class EventResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Event>
)