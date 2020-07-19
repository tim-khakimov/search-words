package com.timkhakimov.searchwords.presentation.containers

import com.timkhakimov.searchwords.domain.boundary.ResultWrapper

/**
 * Created by Timur Khakimov on 19.07.2020
 */
interface Container<T> {

    fun observeData(observer : (T) -> Unit)
}