package com.mz.mozio.pizza_delivery.confirmationSheet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mz.mozio.pizza_delivery.confirmationSheet.ConfirmationEvent
import com.mz.mozio.pizza_delivery.confirmationSheet.viewmodel.ConfirmationViewModel
import com.mz.mozio.pizza_delivery.databinding.DialogConfirmationSheetBinding
import com.mz.mozio.pizza_delivery.pizza_menu.model.OrderModel
import com.mz.mozio.pizza_delivery.pizza_menu.view.PizzaMenuFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConfirmationSheetFragment : BottomSheetDialogFragment() {


    private val binding: DialogConfirmationSheetBinding by lazy {
        DialogConfirmationSheetBinding.inflate(layoutInflater)
    }

    private val viewModel: ConfirmationViewModel by viewModels()

    private val orders: OrderModel by lazy {
        val args: ConfirmationSheetFragmentArgs by navArgs()
        args.order ?: OrderModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.close.setOnClickListener {
            viewModel.navigateBack()
            clearBack()
        }
        handleEvents()
        viewModel.setup(orders)
    }

    private fun handleEvents() {

        lifecycleScope.launch {
            viewModel.events.onEach { event ->
                when (event) {
                    is ConfirmationEvent.OnConfirmClicked -> clearBack()
                }
            }.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect()
        }
    }

    private fun clearBack() {
        setFragmentResult(
            PizzaMenuFragment.PIZZA_MENU_RESET,
            bundleOf()
        )
    }
}
