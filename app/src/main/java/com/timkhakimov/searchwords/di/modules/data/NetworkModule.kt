package com.timkhakimov.searchwords.di.modules.data

import com.google.gson.GsonBuilder
import com.timkhakimov.searchwords.BuildConfig
import com.timkhakimov.searchwords.di.NetworkConstants.BASE_URL
import com.timkhakimov.searchwords.di.NetworkConstants.CLIENT_ABSOLUTE_TIMEOUT
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Timur Khakimov on 19.07.2020
 */
@Module
class NetworkModule {

    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.writeTimeout(CLIENT_ABSOLUTE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CLIENT_ABSOLUTE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CLIENT_ABSOLUTE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(getHttpLoggingInterceptor())
        return builder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        return interceptor
    }
}