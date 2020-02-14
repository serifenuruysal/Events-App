package com.androidapp.eventsapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.eventsapp.R
import com.androidapp.eventsapp.entity.Guest
import kotlinx.android.synthetic.main.item_guest.view.*

/**
 * Created by S.Nur Uysal on 2020-02-11.
 */

class GuestAdapter :
    PagedListAdapter<Guest, GuestAdapter.GuestViewHolder>(GuestDiff) {

    class GuestViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(guest: Guest) {
            view.tv_title.text = "${guest.first_name} ${guest.last_name}"
            view.tv_sub_title.text = guest.company
            view.tv_description.text = "job ttitle:${guest.job_title}"

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GuestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_guest, parent, false)
        return GuestViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val event = getItem(position)

        event
            ?.let { holder.bind(it) }
    }

}

private object GuestDiff : DiffUtil.ItemCallback<Guest>() {

    override fun areItemsTheSame(oldItem: Guest, newItem: Guest) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Guest, newItem: Guest): Boolean {
        // In this case, if items are the same then content will always be the same
        return true
    }

}

