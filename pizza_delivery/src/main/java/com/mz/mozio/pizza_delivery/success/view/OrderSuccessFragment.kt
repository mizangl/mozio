package com.mz.mozio.pizza_delivery.success.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.databinding.FragmentOrderSuccessBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderSuccessFragment : Fragment() {

    private val binding: FragmentOrderSuccessBinding by lazy {
        FragmentOrderSuccessBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.close.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
