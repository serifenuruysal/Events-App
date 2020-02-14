package com.androidapp.eventsapp.data.db.paging

import androidx.paging.DataSource
import com.androidapp.eventsapp.data.db.EventsDao
import com.androidapp.eventsapp.entity.Event

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */
class DBEventsDataSourceFactory(dao: EventsDao) : DataSource.Factory<Int, Event>() {
    private val eventsPageKeyedDataSource = DBEventsDataSource(dao)

    override fun create(): DataSource<Int, Event> {
        return eventsPageKeyedDataSource
    }


}