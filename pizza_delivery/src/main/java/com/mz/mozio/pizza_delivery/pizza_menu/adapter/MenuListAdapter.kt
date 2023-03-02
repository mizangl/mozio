package com.mz.mozio.pizza_delivery.pizza_menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mz.mozio.pizza_delivery.databinding.LayoutItemPizzaBinding
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.pizza_menu.viewmodel.PizzaMenuViewModel

class MenuListAdapter(
    private val viewModel: PizzaMenuViewModel
) : ListAdapter<PizzaModel, PizzaViewItemAdapter>(DiffPizzaCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewItemAdapter {
        return PizzaViewItemAdapter(
            LayoutItemPizzaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PizzaViewItemAdapter, position: Int) {
        holder.bind(getItem(position), viewModel)
    }
}

class DiffPizzaCallBack : DiffUtil.ItemCallback<PizzaModel>() {

    override fun areItemsTheSame(oldItem: PizzaModel, newItem: PizzaModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: PizzaModel, newItem: PizzaModel): Boolean =
        oldItem == newItem

}