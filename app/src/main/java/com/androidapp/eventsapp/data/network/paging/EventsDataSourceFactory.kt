package com.androidapp.eventsapp.data.network.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.androidapp.eventsapp.entity.Event
import rx.subjects.ReplaySubject

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */

/*
    Responsible for creating the DataSource so we can give it to the PagedList.
 */
class EventsDataSourceFactory : DataSource.Factory<Int, Event>() {
    private val networkStatus: MutableLiveData<EventsPageKeyedDataSource> = MutableLiveData()
    private val pageKeyedDataSource = EventsPageKeyedDataSource()

   override fun create(): DataSource<Int, Event> {
        networkStatus.postValue(pageKeyedDataSource)
        return pageKeyedDataSource
    }

    fun getEvents(): ReplaySubject<Event>? {
        return pageKeyedDataSource.getEvents()
    }

}
