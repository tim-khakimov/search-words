package com.timkhakimov.searchwords.di

/**
 * Created by Timur Khakimov on 19.07.2020
 */
object NetworkConstants {
    const val BASE_URL = "https://dictionary.skyeng.ru/"
    const val CLIENT_ABSOLUTE_TIMEOUT = 60L   //время ожидания клиента (не ретрофита с RX)
    const val HTTP_CACHE_SIZE_MB = 10   //размер кэша в MB
}