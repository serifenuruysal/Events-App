package com.androidapp.eventsapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.eventsapp.R
import com.androidapp.eventsapp.rx.RxBus
import com.androidapp.eventsapp.ui.adapters.EventsAdapter
import com.androidapp.eventsapp.ui.event.EventClickEvent
import com.androidapp.eventsapp.ui.viewmodel.EventsViewModel
import com.androidapp.eventsapp.ui.viewmodel.EventsViewModelFactory
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_events.*

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

class EventsListFragment : Fragment() {
    lateinit var adapter: EventsAdapter

    lateinit var eventListViewModel: EventsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventListViewModel =
            ViewModelProviders.of(this, EventsViewModelFactory(context!!)).get(
                EventsViewModel::class.java
            )
        initEventsList()
        initSubscriptions()

    }


    private fun initSubscriptions() {
        eventListViewModel.getEvents().observe(this, Observer {

            adapter.submitList(it)
        })

    }

    private fun initEventsList() {

        adapter = EventsAdapter()
        rv_events.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_events.adapter = adapter
    }



}