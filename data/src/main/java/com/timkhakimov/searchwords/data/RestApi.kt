package com.timkhakimov.searchwords.data

import com.timkhakimov.searchwords.domain.data.model.Word
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Timur Khakimov on 19.07.2020
 */
interface RestApi {

    @GET("/api/public/v1/words/search")
    fun searchWords(@Query("search") query : String): Single<Response<List<Word>>>
}