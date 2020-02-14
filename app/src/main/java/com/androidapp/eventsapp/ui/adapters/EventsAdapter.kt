package com.androidapp.eventsapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.eventsapp.R
import com.androidapp.eventsapp.entity.Event
import com.androidapp.eventsapp.rx.RxBus
import com.androidapp.eventsapp.ui.event.EventClickEvent
import kotlinx.android.synthetic.main.item_events.view.*

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

class EventsAdapter :
    PagedListAdapter<Event, EventsAdapter.EventViewHolder>(EventDiff) {

    class EventViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(event: Event) {
            view.tv_title.text = event.name
            view.tv_sub_title.text = event.starts
            view.tv_description.text = event.ends

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_events, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)

        event
            ?.let { holder.bind(it) }
        holder.view.setOnClickListener {
            RxBus.publish(EventClickEvent(event!!))
        }
    }

}

private object EventDiff : DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        // In this case, if items are the same then content will always be the same
        return true
    }

}

