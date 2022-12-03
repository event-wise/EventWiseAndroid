package com.example.eventwise.groupdetails

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.models.EventModel
import com.example.eventwise.models.MemberModel

@Suppress("unchecked_cast")
@BindingAdapter("memberListBindingAdapter")
fun memberListBindingAdapter(recyclerView: RecyclerView, memberList: List<MemberModel>?) {
    val adapter = recyclerView.adapter as? ListAdapter<MemberModel, MemberItemViewHolder>
    adapter?.submitList(memberList.orEmpty())
}

@Suppress("unchecked_cast")
@BindingAdapter("activeEventsListBindingAdapter")
fun activeEventsListBindingAdapter(recyclerView: RecyclerView, activeEventList: List<EventModel>?) {
    val adapter = recyclerView.adapter as? ListAdapter<EventModel, ActiveEventItemViewHolder>
    adapter?.submitList(activeEventList.orEmpty())
}

@Suppress("unchecked_cast")
@BindingAdapter("logListBindingAdapter")
fun logListBindingAdapter(recyclerView: RecyclerView, logList: List<String>?) {
    val adapter = recyclerView.adapter as? ListAdapter<String, LogItemViewHolder>
    adapter?.submitList(logList.orEmpty())
}
