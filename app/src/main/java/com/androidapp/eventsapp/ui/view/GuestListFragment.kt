package com.androidapp.eventsapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.eventsapp.R
import com.androidapp.eventsapp.ui.adapters.GuestAdapter
import com.androidapp.eventsapp.ui.viewmodel.GuestViewModel
import com.androidapp.eventsapp.ui.viewmodel.GuestsViewModelFactory
import kotlinx.android.synthetic.main.fragment_guest_list.*
import kotlinx.android.synthetic.main.fragment_guest_list.toolbar

/**
 * Created by S.Nur Uysal on 2020-02-12.
 */

class GuestListFragment : Fragment() {

    private var eventId: Int? = null
    lateinit var adapter: GuestAdapter
    lateinit var guestListViewModel: GuestViewModel

    companion object {
        fun newInstance(eventId: Int): GuestListFragment {
            val fragment = GuestListFragment()
            val arguments = Bundle()
            arguments.putInt("eventId", eventId)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_guest_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            eventId = arguments!!.getInt("eventId")
        }

        guestListViewModel =
            ViewModelProviders.of(this, GuestsViewModelFactory(context!!,eventId!!)).get(
                GuestViewModel::class.java
            )


        setToolbar()
        initGuestsList()
        initSubscriptions()

    }

    private fun initGuestsList() {

        adapter = GuestAdapter()
        rv_guests.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_guests.adapter = adapter
    }


    private fun initSubscriptions() {
        guestListViewModel.getGuests().observe(this, Observer {

            adapter.submitList(it)
        })
    }


    private fun setToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}