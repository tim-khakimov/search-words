package com.timkhakimov.searchwords.domain.boundary

/**
 * Created by Timur Khakimov on 19.07.2020
 * Обертка для пересечения границ между domain и presentation
 */
data class ResultWrapper<T> (
    val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null
)

enum class Status {
    LOADING,
    ERROR,
    SUCCESS
}