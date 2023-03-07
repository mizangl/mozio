package com.mz.mozio.pizza_delivery.core.navigation

import androidx.navigation.NavController
import com.mz.mozio.pizza_delivery.core.di.NavigationComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import javax.inject.Provider

@ActivityRetainedScoped
class RootCoordinator @Inject constructor(
    private val navigationComponentProvider: Provider<NavigationComponent.Builder>
) {

    var navigationComponent: NavigationComponent? = null
        private set

    fun setupNavigationComponent(navController: NavController) {
        navigationComponent =
            navigationComponentProvider.get().setNavController(navController).build()
    }

    fun clear() {
        navigationComponent = null
    }
}
