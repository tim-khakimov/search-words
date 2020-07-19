package com.timkhakimov.searchwords.domain.interactors

import com.timkhakimov.searchwords.domain.boundary.OutputBoundary
import com.timkhakimov.searchwords.domain.boundary.ResultWrapper
import com.timkhakimov.searchwords.domain.boundary.ResultWrapperUtils
import com.timkhakimov.searchwords.domain.boundary.Status
import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.domain.data.model.Word
import com.timkhakimov.searchwords.domain.data.source.Repository
import com.timkhakimov.searchwords.domain.data.source.Response

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class SearchWordsInteractor(
    val repository: Repository,
    val wordsOutputBoundary: OutputBoundary<ResultWrapper<List<Meaning>>>
) {

    fun search(query: String) {
        wordsOutputBoundary.sendData(ResultWrapper(Status.LOADING))
        repository.searchWords(query) {
            handleResponse(it)
        }
    }

    private fun handleResponse(response: Response<List<Meaning>>) {
        wordsOutputBoundary.sendData(ResultWrapperUtils.createResultWrapper(response))
    }
}