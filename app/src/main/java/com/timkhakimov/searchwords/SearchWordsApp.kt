package com.timkhakimov.searchwords

import android.app.Application
import com.timkhakimov.searchwords.di.AppComponent
import com.timkhakimov.searchwords.di.DaggerAppComponent

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class SearchWordsApp : Application() {

    lateinit var component : AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().build()
    }
}