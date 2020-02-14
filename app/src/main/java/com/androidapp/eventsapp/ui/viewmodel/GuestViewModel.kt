package com.androidapp.eventsapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.androidapp.eventsapp.data.GuestsRepository
import com.androidapp.eventsapp.entity.Guest

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */


class GuestViewModel(context: Context,val eventId: Int) :
    ViewModel() {
    private val repository: GuestsRepository = GuestsRepository(context)

    fun getGuests(): LiveData<PagedList<Guest>> {
        return repository.getGuests(eventId)
    }

}