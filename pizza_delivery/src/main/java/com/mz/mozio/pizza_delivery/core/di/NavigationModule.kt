package com.mz.mozio.pizza_delivery.core.di

import androidx.navigation.NavController
import com.mz.mozio.pizza_delivery.core.navigation.RootCoordinator
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(NavigationComponent::class)
@EntryPoint
interface NavigationEntryPoint {
    fun navController(): NavController
}


@InstallIn(ViewModelComponent::class)
@Module
object NavigationModule {

    @Provides
    fun provideNavController(
        rootCoordinator: RootCoordinator
    ): NavController {
        return EntryPoints.get(
            rootCoordinator.navigationComponent,
            NavigationEntryPoint::class.java
        ).navController()
    }
}