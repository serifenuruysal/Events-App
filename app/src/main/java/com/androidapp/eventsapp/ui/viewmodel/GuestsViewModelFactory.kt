package com.androidapp.eventsapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

@Suppress("UNCHECKED_CAST")
class GuestsViewModelFactory(
    private val context: Context,
    private val eventId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GuestViewModel(context = context, eventId = eventId) as T
    }
}