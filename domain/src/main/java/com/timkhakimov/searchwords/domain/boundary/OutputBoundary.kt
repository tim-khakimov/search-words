package com.timkhakimov.searchwords.domain.boundary

/**
 * Created by Timur Khakimov on 19.07.2020
 * Для отправки данных из domain в presentation слой
 */
interface OutputBoundary<D> {
    fun sendData(data : D)
}