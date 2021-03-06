package com.timkhakimov.searchwords.domain.data.source

import com.timkhakimov.searchwords.domain.data.model.Meaning

/**
 * Created by Timur Khakimov on 19.07.2020
 * Интерфейс для получения данных
 */
interface Repository {

    fun searchWords(query : String, callback: (Response<List<Meaning>>) -> Unit)

    fun getMeaning(id : Int, callback: (Response<Meaning>) -> Unit)
}