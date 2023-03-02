package com.mz.mozio.pizza_delivery.pizza_menu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mz.mozio.pizza_delivery.databinding.FragmentPizzaMenuBinding
import com.mz.mozio.pizza_delivery.pizza_menu.OnLoadMenu
import com.mz.mozio.pizza_delivery.pizza_menu.OnSelectedPizza
import com.mz.mozio.pizza_delivery.pizza_menu.viewmodel.PizzaMenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

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
    }

    private fun handleEvents() {
        lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is OnLoadMenu -> {
                        viewModel.loadPizzas()
                    }

                    is OnSelectedPizza -> {
                        Timber.d("${event.data}")
                        // TODO () navigate to bottom-sheet
                    }
                }
            }
        }
    }
}