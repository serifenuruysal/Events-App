package com.androidapp.eventsapp.data.network.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.androidapp.eventsapp.entity.Guest
import rx.subjects.ReplaySubject

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */

/*
    Responsible for creating the DataSource so we can give it to the PagedList.
 */
class GuestsDataSourceFactory : DataSource.Factory<Int, Guest>() {
    private val networkStatus: MutableLiveData<GuestsPageKeyedDataSource> = MutableLiveData()
    private val pageKeyedDataSource = GuestsPageKeyedDataSource()

   override fun create(): DataSource<Int, Guest> {
        networkStatus.postValue(pageKeyedDataSource)
        return pageKeyedDataSource
    }

    fun getGuests(eventId:Int): ReplaySubject<Guest>? {
        return pageKeyedDataSource.getGuests(eventId)
    }

}
