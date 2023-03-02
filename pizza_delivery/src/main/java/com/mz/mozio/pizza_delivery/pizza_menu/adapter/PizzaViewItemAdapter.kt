package com.mz.mozio.pizza_delivery.pizza_menu.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mz.mozio.pizza_delivery.databinding.LayoutItemPizzaBinding
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.pizza_menu.viewmodel.PizzaMenuViewModel

class PizzaViewItemAdapter(
    private val binding: LayoutItemPizzaBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: PizzaModel, viewModel: PizzaMenuViewModel) {
        binding.viewModel = viewModel
        binding.item = data
        binding.executePendingBindings()
    }
}
