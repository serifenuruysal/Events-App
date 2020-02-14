package com.androidapp.eventsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidapp.eventsapp.entity.Event

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: Event)


    @Query("DELETE FROM event")
    fun deleteAll()

    @Query("SELECT * FROM event")
    fun getEvents(): List<Event>?

}
