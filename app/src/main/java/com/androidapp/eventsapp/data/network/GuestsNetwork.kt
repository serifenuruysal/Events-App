package com.androidapp.eventsapp.data.network

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidapp.eventsapp.data.network.paging.GuestsDataSourceFactory
import com.androidapp.eventsapp.entity.Guest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */

class GuestsNetwork(
    dataSourceFactory: GuestsDataSourceFactory,
    boundaryCallback: PagedList.BoundaryCallback<Guest>
) {
    private val guestsPaged: LiveData<PagedList<Guest>>

    fun getPagedGuests(): LiveData<PagedList<Guest>> {
        return guestsPaged
    }

    init {
        val pagedListConfig: PagedList.Config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10).setPageSize(10).build()
        val executor: Executor = Executors.newFixedThreadPool(3)

        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)

        guestsPaged = livePagedListBuilder.setFetchExecutor(executor).setBoundaryCallback(boundaryCallback).build()
    }


}