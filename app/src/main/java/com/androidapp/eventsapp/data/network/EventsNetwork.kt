package com.androidapp.eventsapp.data.network

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidapp.eventsapp.entity.Event
import com.androidapp.eventsapp.data.network.paging.EventsDataSourceFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */

class EventsNetwork(
    dataSourceFactory: EventsDataSourceFactory,
    boundaryCallback: PagedList.BoundaryCallback<Event>
) {
    private val eventsPaged: LiveData<PagedList<Event>>
//    private val networkState: LiveData<NetworkState>

    fun getPagedEvents(): LiveData<PagedList<Event>> {
        return eventsPaged
    }
//
//    fun getNetworkState(): LiveData<NetworkState> {
//        return networkState
//    }

    companion object {
        private val TAG = EventsNetwork::class.java.simpleName
    }

    init {
        val pagedListConfig: PagedList.Config = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10).setPageSize(10).build()

//        networkState = Transformations.switchMap(
//            dataSourceFactory.getNetworkStatus(),
//            EventsPageKeyedDataSource::getNetworkState as Function<EventsPageKeyedDataSource, LiveData<NetworkState>>
//        )
        val executor: Executor = Executors.newFixedThreadPool(3)

        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)

        eventsPaged = livePagedListBuilder.setFetchExecutor(executor).setBoundaryCallback(boundaryCallback).build()
    }


}