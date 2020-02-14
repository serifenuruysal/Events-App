package com.androidapp.eventsapp.data.network.api

import com.androidapp.eventsapp.entity.EventResponse
import com.androidapp.eventsapp.entity.GuestResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */

interface ApiInterface {
    @GET("events?group_id=59578")
    fun getEvents(@Query("page") page: Int): Call<EventResponse>

    @GET("events/{event_id}/guests")
    fun getGuests(@Path("event_id") eventId: Int, @Query("page") page: Int): Call<GuestResponse>
}