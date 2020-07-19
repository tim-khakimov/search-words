package com.timkhakimov.searchwords.di

import com.timkhakimov.searchwords.di.modules.domain.InteractorsModule
import com.timkhakimov.searchwords.presentation.MeaningViewModel
import com.timkhakimov.searchwords.presentation.SearchWordsViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Timur Khakimov on 19.07.2020
 */
@Singleton
@Component(modules = [InteractorsModule::class])
interface AppComponent {
    fun inject(searchWordsViewModel: SearchWordsViewModel)
    fun inject(meaningViewModel: MeaningViewModel)
}