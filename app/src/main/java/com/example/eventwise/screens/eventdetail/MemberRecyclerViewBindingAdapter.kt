package com.example.eventwise.screens.eventdetail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventwise.models.MemberModel

@Suppress("unchecked_cast")
@BindingAdapter("memberListBindingAdapter")
fun memberListBindingAdapter(recyclerView: RecyclerView, memberList: List<String>?) {
    val adapter = recyclerView.adapter as? ListAdapter<String, MemberItemViewHolder>
    adapter?.submitList(memberList.orEmpty())
}
