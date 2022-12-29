package com.example.weatherapp.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.databinding.RowDayForecastBinding
import com.example.weatherapp.databinding.RowEmptyItemBinding
import com.example.weatherapp.databinding.RowLoaderItemBinding
import com.example.weatherapp.ui.common.holder.item.BaseItem
import com.example.weatherapp.ui.common.holder.item.ItemType
import com.example.weatherapp.ui.common.holder.viewholder.BaseViewHolder
import com.example.weatherapp.ui.common.holder.viewholder.EmptyViewHolder
import com.example.weatherapp.ui.common.holder.viewholder.ForecastViewHolder
import com.example.weatherapp.ui.common.holder.viewholder.LoaderViewHolder
import com.squareup.picasso.Picasso
import javax.inject.Inject

class FiveDayForecastAdapter(private val activity: FiveDayForecastActivity): RecyclerView.Adapter<BaseViewHolder<in BaseItem, in ViewBinding>>() {

    @Inject
    lateinit var picasso: Picasso

    init {
        activity.component.inject(this)
    }

    var items: List<BaseItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<in BaseItem, in ViewBinding> {

        val inflater = LayoutInflater.from(parent.context)

        @Suppress("UNCHECKED_CAST")
        return when(ItemType.fromValue(viewType)) {
            ItemType.Forecast -> ForecastViewHolder(RowDayForecastBinding.inflate(inflater, parent, false), activity, picasso)
            ItemType.Loader -> LoaderViewHolder(RowLoaderItemBinding.inflate(inflater, parent, false))
            ItemType.Empty -> EmptyViewHolder(RowEmptyItemBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("Unknown viewType")
        } as BaseViewHolder<in BaseItem, in ViewBinding>
    }

    override fun getItemViewType(position: Int) = items[position].itemType.value

    override fun onBindViewHolder(
        holder: BaseViewHolder<in BaseItem, in ViewBinding>,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size


}