package com.example.weatherapp.ui.common.holder.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.ui.common.holder.item.BaseItem

abstract class BaseViewHolder<T: BaseItem, D: ViewBinding>(val binding: D): RecyclerView.ViewHolder(binding.root){
    abstract fun bind(item: T)
}