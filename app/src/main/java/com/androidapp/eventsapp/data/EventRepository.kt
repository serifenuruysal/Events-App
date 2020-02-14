package com.androidapp.eventsapp.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import com.androidapp.eventsapp.data.db.BoomsetDb
import com.androidapp.eventsapp.entity.Event
import com.androidapp.eventsapp.data.network.EventsNetwork
import com.androidapp.eventsapp.data.network.paging.EventsDataSourceFactory
import rx.schedulers.Schedulers

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

class EventRepository(context: Context) {
    private var network: EventsNetwork
    private var database: BoomsetDb = BoomsetDb.getInstance(context.applicationContext)!!
    private val liveDataMerger = MediatorLiveData<PagedList<Event>>()


    init {
        val boundaryCallback: PagedList.BoundaryCallback<Event> =
            object : PagedList.BoundaryCallback<Event>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                    liveDataMerger.addSource(database.getEvents()!!) {
                        liveDataMerger.value = it
                        liveDataMerger.removeSource(database.getEvents()!!)
                    }
                }
            }

        val dataSourceFactory = EventsDataSourceFactory().apply {
            network = EventsNetwork(this, boundaryCallback)
        }

        liveDataMerger.addSource(network.getPagedEvents()) {
            liveDataMerger.value = it
            Log.d(
                "EventRepository",
                it.toString()
            )
        }

        dataSourceFactory.getEvents()?.observeOn(Schedulers.io())?.subscribe {
            database.eventDao().insert(it)
        }

    }

    companion object {
        private var instance: EventRepository? = null

        fun getInstance(context: Context): EventRepository {
            if (instance == null) {
                instance = EventRepository(context)
            }
            return instance!!
        }
    }

    fun getEvents(): LiveData<PagedList<Event>> {
        return liveDataMerger
    }

//    fun getNetworkState(): LiveData<NetworkState?>? {
//        return network.getNetworkState()
//    }


}