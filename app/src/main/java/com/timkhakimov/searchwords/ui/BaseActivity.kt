package com.timkhakimov.searchwords.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Timur Khakimov on 19.07.2020
 */
open class BaseActivity : AppCompatActivity() {

    protected fun <VM : ViewModel> getViewModel(viewModelClass: Class<VM>): VM {
        val provider = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
        return provider.get(viewModelClass)
    }
}