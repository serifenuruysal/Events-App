package com.androidapp.eventsapp.data.network.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.androidapp.eventsapp.data.network.api.ApiService
import com.androidapp.eventsapp.entity.Event
import com.androidapp.eventsapp.entity.EventResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.subjects.ReplaySubject

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */
class EventsPageKeyedDataSource : PageKeyedDataSource<Int, Event>() {
    private val service = ApiService()
    private val networkState = MutableLiveData<NetworkState>()
    private val eventsObservable: ReplaySubject<Event> = ReplaySubject.create()

    fun getEvents(): ReplaySubject<Event> {
        return eventsObservable
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Event>
    ) {

        val call = service.getEvents(1)
        call.enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.results
                    responseItems.let {
                        //                        Log.d("serife", "loadInitial getTrendingRepos")
                        callback.onResult(responseItems, 1, 2)
                        responseItems.forEach {
                            eventsObservable.onNext(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Log.d("serife", "onFailure")
                callback.onResult(arrayListOf(), 1, 2)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
        val call = service.getEvents(params.key)
        call.enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.results
                    val key = params.key + 1
                    responseItems.let {
                        //                        Log.d("serife", "loadAfter getTrendingRepos")
                        callback.onResult(responseItems, key)
                        responseItems.forEach {
                            eventsObservable.onNext(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Log.d("serife", "onFailure")
                callback.onResult(arrayListOf(), params.key )
            }
        })
    }


}
