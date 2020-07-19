package com.timkhakimov.searchwords.di.modules.data

import com.timkhakimov.searchwords.data.LocalMeaningsStore
import com.timkhakimov.searchwords.data.RemoteDataSource
import com.timkhakimov.searchwords.data.RestApi
import com.timkhakimov.searchwords.data.WordsRepository
import com.timkhakimov.searchwords.domain.data.source.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Timur Khakimov on 19.07.2020
 */
@Module(includes = [NetworkModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideRepository(retrofit: Retrofit) : Repository {
        return WordsRepository(RemoteDataSource(retrofit.create(RestApi::class.java)), LocalMeaningsStore())
    }
}