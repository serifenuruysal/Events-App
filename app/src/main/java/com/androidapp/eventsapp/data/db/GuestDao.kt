package com.androidapp.eventsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidapp.eventsapp.entity.Guest

/**
 * Created by S.Nur Uysal on 2020-02-12.
 */
@Dao
interface GuestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(guest: Guest)


    @Query("DELETE FROM guest")
    fun deleteAll()

    @Query("SELECT * FROM guest where event=:eventId")
    fun getGuestByEventId(eventId: Int): List<Guest>?

}