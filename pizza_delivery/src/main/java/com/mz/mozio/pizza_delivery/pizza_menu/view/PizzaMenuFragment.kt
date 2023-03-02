package com.mz.mozio.pizza_delivery.pizza_menu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.confirmationSheet.view.ConfirmationSheetFragmentArgs
import com.mz.mozio.pizza_delivery.databinding.FragmentPizzaMenuBinding
import com.mz.mozio.pizza_delivery.pizza_menu.OnLoadMenu
import com.mz.mozio.pizza_delivery.pizza_menu.OnSelectedPizza
import com.mz.mozio.pizza_delivery.pizza_menu.OnSelectedTwoHalf
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.pizza_menu.viewmodel.PizzaMenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        handleEvents()
        viewModel.loadPizzas()
        handleNavigationListener()
    }

    private fun handleNavigationListener() {
        parentFragmentManager.setFragmentResultListener(PIZZA_MENU_RESET, this) { _, _ ->
            viewModel.clearSelection()
        }
    }

    private fun handleEvents() {
        lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is OnLoadMenu -> {
                        viewModel.loadPizzas()
                    }

                    is OnSelectedPizza -> {
                        navigateToConfirmation(event.data)
                    }
                    is OnSelectedTwoHalf -> {
                        navigateToConfirmation(event.data)
                    }
                }
            }
        }
    }

    private fun navigateToConfirmation(order: List<PizzaModel>) {
        val args = ConfirmationSheetFragmentArgs(
            orders = order.toTypedArray()
        ).toBundle()
        findNavController().navigate(
            R.id.dialog_pizza_confirmation,
            args
        )
    }

    companion object {
        const val PIZZA_MENU_RESET = "pizza_menu_reset"
    }
}
