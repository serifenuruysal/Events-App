package com.androidapp.eventsapp.data.db.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.androidapp.eventsapp.data.db.EventsDao
import com.androidapp.eventsapp.entity.Event

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

class DBEventsDataSource(private val movieDao: EventsDao) :
    PageKeyedDataSource<Int, Event>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Event>
    ) {
        Log.i(
            TAG,
            "Loading Initial Rang, Count " + params.requestedLoadSize
        )
        val events: List<Event>? = movieDao.getEvents()
        if (events != null && events.isNotEmpty()) {
            callback.onResult(events,null, 1)
        }
    }

   override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {}
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {}

    companion object {
        val TAG = DBEventsDataSource::class.java.simpleName
    }

}
