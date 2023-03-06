package com.mz.mozio.pizza_delivery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.mz.mozio.pizza_delivery.core.navigation.RootCoordinator
import com.mz.mozio.pizza_delivery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var rootCoordinator: RootCoordinator

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navController by lazy {
        (binding.navHostFragmentContainer.getFragment() as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        rootCoordinator.setupNavigationComponent(navController)
    }

    override fun onDestroy() {
        rootCoordinator.clear()
        super.onDestroy()
    }
}
