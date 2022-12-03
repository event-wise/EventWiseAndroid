package com.example.eventwise.screens.groupdetails

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.models.EventsModel
import com.example.eventwise.models.MemberModel

@Suppress("unchecked_cast")
@BindingAdapter("memberListBindingAdapter")
fun memberListBindingAdapter(recyclerView: RecyclerView, memberList: List<String>?) {
    val adapter = recyclerView.adapter as? ListAdapter<String, MemberItemViewHolder>
    adapter?.submitList(memberList.orEmpty())
}

@Suppress("unchecked_cast")
@BindingAdapter("activeEventsListBindingAdapter")
fun activeEventsListBindingAdapter(recyclerView: RecyclerView, activeEventList: List<EventsModel>?) {
    val adapter = recyclerView.adapter as? ListAdapter<EventsModel, ActiveEventItemViewHolder>
    adapter?.submitList(activeEventList.orEmpty())
}

@Suppress("unchecked_cast")
@BindingAdapter("logListBindingAdapter")
fun logListBindingAdapter(recyclerView: RecyclerView, logList: List<String>?) {
    val adapter = recyclerView.adapter as? ListAdapter<String, LogItemViewHolder>
    adapter?.submitList(logList.orEmpty())
}
