package com.timkhakimov.searchwords.domain.data.source

/**
 * Created by Timur Khakimov on 19.07.2020
 * Результат выполнения запросов
 * Либо данные, либо ошибка
 */
data class Response<T>(
    val data: T?,
    val throwable: Throwable?
)