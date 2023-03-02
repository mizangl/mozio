package com.mz.mozio.pizza_delivery.pizza_menu

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mz.mozio.pizza_delivery.pizza_menu.adapter.MenuListAdapter
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.pizza_menu.viewmodel.PizzaMenuViewModel

@BindingAdapter(
    "menu",
    "viewModel"
)
fun RecyclerView.bindMenu(
    menu: List<PizzaModel>?,
    viewModel: PizzaMenuViewModel
) {
    if (layoutManager == null) layoutManager = LinearLayoutManager(context)
    if (adapter == null) adapter = MenuListAdapter(viewModel)
    (adapter as MenuListAdapter).submitList(menu)
}