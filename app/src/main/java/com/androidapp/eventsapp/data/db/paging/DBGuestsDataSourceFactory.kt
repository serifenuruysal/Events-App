package com.androidapp.eventsapp.data.db.paging

import androidx.paging.DataSource
import com.androidapp.eventsapp.data.db.GuestDao
import com.androidapp.eventsapp.entity.Guest

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */
class DBGuestsDataSourceFactory(dao: GuestDao, eventId: Int) : DataSource.Factory<Int, Guest>() {
    private val guestsPageKeyedDataSource = DBGuestsDataSource(dao,eventId)

    override fun create(): DataSource<Int, Guest> {
        return guestsPageKeyedDataSource
    }


}