package com.androidapp.eventsapp.data.network.api

import com.androidapp.eventsapp.entity.EventResponse
import com.androidapp.eventsapp.entity.GuestResponse
import retrofit2.Call

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */

interface BoomsetApi {

    fun getEvents(page: Int): Call<EventResponse>


    fun getGuests(page: Int, eventId: Int): Call<GuestResponse>
}