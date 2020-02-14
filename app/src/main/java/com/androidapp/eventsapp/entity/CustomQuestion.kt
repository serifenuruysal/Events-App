package com.androidapp.eventsapp.entity

import com.androidapp.eventsapp.entity.Answer

data class CustomQuestion(
    val answer: List<Answer>,
    val objstatus: String,
    val question_id: Int,
    val question_text: String
)