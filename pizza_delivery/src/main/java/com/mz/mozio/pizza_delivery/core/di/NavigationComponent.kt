package com.mz.mozio.pizza_delivery.core.di

import androidx.navigation.NavController
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Scope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class NavigationScope


@DefineComponent(parent = ActivityRetainedComponent::class)
@NavigationScope
interface NavigationComponent {

    @DefineComponent.Builder
    interface Builder {
        fun setNavController(@BindsInstance navController: NavController): Builder
        fun build(): NavigationComponent
    }
}
