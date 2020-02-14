package com.androidapp.eventsapp.data.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidapp.eventsapp.data.db.paging.DBEventsDataSourceFactory
import com.androidapp.eventsapp.data.db.paging.DBGuestsDataSourceFactory
import com.androidapp.eventsapp.entity.Event
import com.androidapp.eventsapp.entity.Guest
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

@Database(entities = [Event::class, Guest::class], version = 1, exportSchema = false)
abstract class BoomsetDb : RoomDatabase() {
    abstract fun eventDao(): EventsDao
    abstract fun guestDao(): GuestDao
    private var eventsPaged: LiveData<PagedList<Event>>? = null
    private var guestPaged: LiveData<PagedList<Guest>>? = null
    private lateinit var pagedListConfig: PagedList.Config

    private fun init() {
        pagedListConfig =
            PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Int.MAX_VALUE).setPageSize(Int.MAX_VALUE)
                .build()

    }

    fun getEvents(): LiveData<PagedList<Event>>? {

        val executor: Executor =
            Executors.newFixedThreadPool(3)
        val dataSourceFactory = DBEventsDataSourceFactory(eventDao())
        val livePagedListBuilder =
            LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        eventsPaged = livePagedListBuilder.setFetchExecutor(executor).build()
        return eventsPaged
    }

    fun getGuests(eventId: Int): LiveData<PagedList<Guest>>? {

        val executor: Executor =
            Executors.newFixedThreadPool(3)

        val guestDataSourceFactory = DBGuestsDataSourceFactory(guestDao(),eventId)
        val guestLivePagedListBuilder =
            LivePagedListBuilder(guestDataSourceFactory, pagedListConfig)
        guestPaged = guestLivePagedListBuilder.setFetchExecutor(executor).build()
        return guestPaged
    }

    companion object {
        private var instance: BoomsetDb? = null
        private val sLock = Any()
        fun getInstance(context: Context): BoomsetDb? {
            synchronized(sLock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BoomsetDb::class.java, "boomset.db"
                    )
                        .build()
                    instance!!.init()
                }
                return instance
            }
        }
    }
}
