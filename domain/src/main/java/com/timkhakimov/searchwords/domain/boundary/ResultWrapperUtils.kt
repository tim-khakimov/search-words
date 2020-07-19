package com.timkhakimov.searchwords.domain.boundary

import com.timkhakimov.searchwords.domain.data.source.Response

/**
 * Created by Timur Khakimov on 19.07.2020
 */
object ResultWrapperUtils {

    fun <T> createResultWrapper(response: Response<T>) : ResultWrapper<T> {
        val status = if(response.data != null) Status.SUCCESS else Status.ERROR
        return ResultWrapper(status, response.data, response.throwable)
    }
}