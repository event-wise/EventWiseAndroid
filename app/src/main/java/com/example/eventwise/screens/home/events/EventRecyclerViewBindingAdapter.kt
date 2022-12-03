package com.example.eventwise.screens.home.events

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.models.EventsModel

@Suppress("unchecked_cast")
@BindingAdapter("eventListBindingAdapter")
fun eventListBindingAdapter(recyclerView: RecyclerView, eventList: List<EventsModel>?) {
    val adapter = recyclerView.adapter as? ListAdapter<EventsModel, EventItemViewHolder>
    adapter?.submitList(eventList.orEmpty())
}
