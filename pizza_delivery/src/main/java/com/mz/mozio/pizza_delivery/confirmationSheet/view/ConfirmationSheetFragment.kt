package com.mz.mozio.pizza_delivery.confirmationSheet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.confirmationSheet.ConfirmationEvent
import com.mz.mozio.pizza_delivery.confirmationSheet.viewmodel.ConfirmationViewModel
import com.mz.mozio.pizza_delivery.databinding.DialogConfirmationSheetBinding
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.pizza_menu.view.PizzaMenuFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConfirmationSheetFragment : BottomSheetDialogFragment() {


    private val binding: DialogConfirmationSheetBinding by lazy {
        DialogConfirmationSheetBinding.inflate(layoutInflater)
    }

    private val viewModel: ConfirmationViewModel by viewModels()

    private val orders: List<PizzaModel> by lazy {
        val args: ConfirmationSheetFragmentArgs by navArgs()
        args.orders?.toList() ?: emptyList()
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
            navigateBack()
        }
        handleEvents()

        viewModel.setup(orders)
    }

    private fun navigateBack() {
        setFragmentResult(PizzaMenuFragment.PIZZA_MENU_RESET, bundleOf())
        findNavController().popBackStack()
    }

    private fun handleEvents() {
        lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is ConfirmationEvent.OnConfirmClicked -> {
                        navigateBack()
                        navigateToConfirm()
                    }
                }
            }
        }
    }

    private fun navigateToConfirm() {
        parentFragment?.findNavController()?.navigate(R.id.pizza_succeed)
    }
}
