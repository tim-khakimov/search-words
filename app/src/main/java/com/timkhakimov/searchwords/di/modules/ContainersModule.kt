package com.timkhakimov.searchwords.di.modules

import com.timkhakimov.searchwords.presentation.containers.FoundMeaningsContainer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Timur Khakimov on 19.07.2020
 */
@Module
class ContainersModule {

    @Singleton
    @Provides
    fun provideFoundMeaningsContainer() : FoundMeaningsContainer {
        return FoundMeaningsContainer()
    }
}