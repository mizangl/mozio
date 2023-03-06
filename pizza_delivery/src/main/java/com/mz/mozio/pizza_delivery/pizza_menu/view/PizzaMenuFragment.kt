package com.mz.mozio.pizza_delivery.pizza_menu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mz.mozio.pizza_delivery.databinding.FragmentPizzaMenuBinding
import com.mz.mozio.pizza_delivery.pizza_menu.viewmodel.PizzaMenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PizzaMenuFragment : Fragment() {

    private val binding: FragmentPizzaMenuBinding by lazy {
        FragmentPizzaMenuBinding.inflate(layoutInflater)
    }

    private val viewModel: PizzaMenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleNavigationListener()
    }

    private fun handleNavigationListener() {
        parentFragmentManager.setFragmentResultListener(PIZZA_MENU_RESET, this) { _, _ ->
            viewModel.clearSelection()
        }
    }

    companion object {
        const val PIZZA_MENU_RESET = "pizza_menu_reset"
    }
}
