package com.androidapp.eventsapp.data.network.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.androidapp.eventsapp.data.network.api.ApiService
import com.androidapp.eventsapp.entity.Guest
import com.androidapp.eventsapp.entity.GuestResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.subjects.ReplaySubject

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */
class GuestsPageKeyedDataSource : PageKeyedDataSource<Int, Guest>() {
    private val service = ApiService()
    private val guestsObservable: ReplaySubject<Guest> = ReplaySubject.create()
    private var eventId: Int?=null

    fun getGuests(eventId: Int): ReplaySubject<Guest> {
        this.eventId = eventId
        return guestsObservable
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Guest>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Guest>
    ) {

        val call = service.getGuests(eventId!!, 1)
        call.enqueue(object : Callback<GuestResponse> {
            override fun onResponse(call: Call<GuestResponse>, response: Response<GuestResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.results
                    responseItems.let {
                        //                        Log.d("serife", "loadInitial getTrendingRepos")
                        callback.onResult(responseItems!!, 1, 2)
                        responseItems.forEach {
                            guestsObservable.onNext(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<GuestResponse>, t: Throwable) {
                Log.d("serife", "onFailure")
                callback.onResult(arrayListOf(), 1, 2)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Guest>) {
        val call = service.getGuests(eventId!!, params.key)
        call.enqueue(object : Callback<GuestResponse> {
            override fun onResponse(call: Call<GuestResponse>, response: Response<GuestResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.results
                    val key = params.key + 1
                    responseItems.let {
                        //                        Log.d("serife", "loadAfter getTrendingRepos")
                        callback.onResult(responseItems!!, key)
                        responseItems.forEach {
                            guestsObservable.onNext(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<GuestResponse>, t: Throwable) {
                Log.d("serife", "onFailure")
                callback.onResult(arrayListOf(), params.key)
            }
        })
    }


}
