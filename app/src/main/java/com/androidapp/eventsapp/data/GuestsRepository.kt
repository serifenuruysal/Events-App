package com.androidapp.eventsapp.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import com.androidapp.eventsapp.data.db.BoomsetDb
import com.androidapp.eventsapp.data.network.GuestsNetwork
import com.androidapp.eventsapp.data.network.paging.GuestsDataSourceFactory
import com.androidapp.eventsapp.entity.Guest
import rx.schedulers.Schedulers

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

class GuestsRepository(context: Context) {
    private var network: GuestsNetwork
    private var database: BoomsetDb = BoomsetDb.getInstance(context.applicationContext)!!
    private val liveDataMerger = MediatorLiveData<PagedList<Guest>>()
    private var eventId: Int? = null
    private var dataSourceFactory: GuestsDataSourceFactory

    init {
        val boundaryCallback: PagedList.BoundaryCallback<Guest> =
            object : PagedList.BoundaryCallback<Guest>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                    liveDataMerger.addSource(database.getGuests(eventId!!)!!) {
                        liveDataMerger.value = it
                        liveDataMerger.removeSource(database.getGuests(eventId!!)!!)
                    }
                }
            }

        dataSourceFactory = GuestsDataSourceFactory().apply {
            network = GuestsNetwork(this, boundaryCallback)
        }

        liveDataMerger.addSource(network.getPagedGuests()) {
            liveDataMerger.value = it
            Log.d(
                "GuestRepository",
                it.toString()
            )
        }

    }

    fun getGuests(eventId: Int): LiveData<PagedList<Guest>> {
        this.eventId = eventId
        dataSourceFactory.getGuests(eventId)?.observeOn(Schedulers.io())?.subscribe {
            database.guestDao().insert(it)
        }
        return liveDataMerger
    }


}