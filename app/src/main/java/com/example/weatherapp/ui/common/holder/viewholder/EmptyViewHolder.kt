package com.example.weatherapp.ui.common.holder.viewholder

import com.example.weatherapp.databinding.RowEmptyItemBinding
import com.example.weatherapp.ui.common.holder.item.EmptyItem

class EmptyViewHolder(binding: RowEmptyItemBinding): BaseViewHolder<EmptyItem, RowEmptyItemBinding>(binding) {
    override fun bind(item: EmptyItem) {}
}