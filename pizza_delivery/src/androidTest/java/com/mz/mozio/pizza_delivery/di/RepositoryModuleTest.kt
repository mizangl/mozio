package com.mz.mozio.pizza_delivery.di

import com.mz.mozio.pizza_delivery.core.di.RepositoryModule
import com.mz.mozio.pizza_delivery.data.FakePizzaMenuRepository
import com.mz.mozio.pizza_delivery.data.repository.PizzaMenuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
interface RepositoryModuleTest {

    @Binds
    fun bindPizzaMenuRepository(
        repositoryImp: FakePizzaMenuRepository
    ): PizzaMenuRepository
}