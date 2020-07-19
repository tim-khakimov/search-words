package com.timkhakimov.searchwords.data

import com.timkhakimov.searchwords.data.Hardcode.MEANING_NOT_FOUND
import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.domain.data.source.Repository
import com.timkhakimov.searchwords.domain.data.source.Response

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class WordsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localMeaningsStore: LocalMeaningsStore
) : Repository {

    override fun searchWords(query: String, callback: (Response<List<Meaning>>) -> Unit) {
        remoteDataSource.searchWords(query, { words ->
            val meanings = mutableListOf<Meaning>()
            for (word in words) {
                word.meanings?.let { meanings.addAll(it) }
            }
            localMeaningsStore.saveMeanings(meanings)
            callback.invoke(Response(meanings, null))
        }, { callback.invoke(Response(null, it)) })
    }

    override fun getMeaning(id: Int, callback: (Response<Meaning>) -> Unit) {
        localMeaningsStore.getMeaning(id)?.let { callback.invoke(Response(it, null)) }
            ?: callback.invoke(
                Response(null, Throwable(MEANING_NOT_FOUND))
            )
    }
}