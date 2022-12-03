package com.example.eventwise.home.events

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.models.EventModel

@Suppress("unchecked_cast")
@BindingAdapter("eventListBindingAdapter")
fun eventListBindingAdapter(recyclerView: RecyclerView, eventList: List<EventModel>?) {
    val adapter = recyclerView.adapter as? ListAdapter<EventModel, EventItemViewHolder>
    adapter?.submitList(eventList.orEmpty())
}
