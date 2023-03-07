package com.mz.mozio.pizza_delivery.pizza_menu.navigation

import com.mz.mozio.pizza_delivery.core.navigation.Coordinator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface PizzaMenuModule {

    @Binds
    @ViewModelScoped
    fun bindCoordinator(coordinator: OrderCoordinator): Coordinator<OrderEvent>
}