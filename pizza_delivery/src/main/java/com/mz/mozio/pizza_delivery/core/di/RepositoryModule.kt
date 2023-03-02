package com.mz.mozio.pizza_delivery.core.di

import com.mz.mozio.pizza_delivery.data.repository.PizzaMenuRepository
import com.mz.mozio.pizza_delivery.data.repository.PizzaMenuRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPizzaMenuRepository(
        repositoryImp: PizzaMenuRepositoryImp
    ): PizzaMenuRepository
}
