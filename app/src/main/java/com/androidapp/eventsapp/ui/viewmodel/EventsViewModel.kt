package com.androidapp.eventsapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.androidapp.eventsapp.data.EventRepository
import com.androidapp.eventsapp.entity.Event

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */


class EventsViewModel(context: Context) :
    ViewModel() {
    private val repository: EventRepository = EventRepository.getInstance(context)

    fun getEvents(): LiveData<PagedList<Event>> {
        return repository.getEvents()
    }

}