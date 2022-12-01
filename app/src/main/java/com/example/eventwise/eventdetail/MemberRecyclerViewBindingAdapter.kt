package com.example.eventwise.eventdetail

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
