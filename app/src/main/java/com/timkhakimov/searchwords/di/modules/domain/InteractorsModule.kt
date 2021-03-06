package com.timkhakimov.searchwords.di.modules.domain

import com.timkhakimov.searchwords.di.modules.ContainersModule
import com.timkhakimov.searchwords.di.modules.data.DataModule
import com.timkhakimov.searchwords.domain.data.source.Repository
import com.timkhakimov.searchwords.domain.interactors.FindMeaningInteractor
import com.timkhakimov.searchwords.domain.interactors.SearchWordsInteractor
import com.timkhakimov.searchwords.presentation.containers.FoundMeaningsContainer
import com.timkhakimov.searchwords.presentation.containers.MeaningContainer
import dagger.Module
import dagger.Provides

/**
 * Created by Timur Khakimov on 19.07.2020
 */
@Module(includes = [DataModule::class, ContainersModule::class])
class InteractorsModule {

    @Provides
    fun provideSearchWordsInteractor(repository: Repository, foundMeaningsContainer: FoundMeaningsContainer) : SearchWordsInteractor {
        return SearchWordsInteractor(repository, foundMeaningsContainer)
    }

    @Provides
    fun provideFindMeaningInteractor(repository: Repository, meaningContainer: MeaningContainer) : FindMeaningInteractor {
        return FindMeaningInteractor(repository, meaningContainer)
    }
}