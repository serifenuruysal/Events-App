package com.androidapp.eventsapp.data.db.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.androidapp.eventsapp.data.db.GuestDao
import com.androidapp.eventsapp.entity.Guest

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

class DBGuestsDataSource(private val dao: GuestDao, private val eventId:Int) :
    PageKeyedDataSource<Int, Guest>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Guest>
    ) {
        Log.i(
            DBGuestsDataSource.TAG,
            "Loading Initial Rang, Count " + params.requestedLoadSize
        )
        val guests: List<Guest>? = dao.getGuestByEventId(eventId)
        if (guests != null && guests.isNotEmpty()) {
            callback.onResult(guests,null, 1)
        }
    }

   override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Guest>) {}
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Guest>) {}

    companion object {
        val TAG = DBGuestsDataSource::class.java.simpleName
    }

}
