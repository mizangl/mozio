package com.mz.mozio.pizza_delivery.di

import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.mz.mozio.pizza_delivery.core.di.NavigationModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [NavigationModule::class]
)
object NavigationModuleTest {

    @Provides
    fun provideNavController(
    ): NavController {
        return TestNavHostController(ApplicationProvider.getApplicationContext())
    }
}