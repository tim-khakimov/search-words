package com.timkhakimov.searchwords.di.modules

import com.timkhakimov.searchwords.presentation.containers.FoundMeaningsContainer
import com.timkhakimov.searchwords.presentation.containers.MeaningContainer
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

    @Singleton
    @Provides
    fun provideMeaningContainer() : MeaningContainer {
        return MeaningContainer()
    }
}