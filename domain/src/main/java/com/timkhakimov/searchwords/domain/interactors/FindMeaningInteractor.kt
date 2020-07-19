package com.timkhakimov.searchwords.domain.interactors

import com.timkhakimov.searchwords.domain.boundary.OutputBoundary
import com.timkhakimov.searchwords.domain.boundary.ResultWrapper
import com.timkhakimov.searchwords.domain.boundary.ResultWrapperUtils
import com.timkhakimov.searchwords.domain.boundary.Status
import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.domain.data.source.Repository
import com.timkhakimov.searchwords.domain.data.source.Response

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class FindMeaningInteractor(
    val repository: Repository,
    val meaningOutputBoundary: OutputBoundary<ResultWrapper<Meaning>>
) {

    fun findMeaning(id: Int) {
        meaningOutputBoundary.sendData(ResultWrapper(Status.LOADING))
        repository.getMeaning(id) { handleResponse(it) }
    }

    private fun handleResponse(response: Response<Meaning>) {
        meaningOutputBoundary.sendData(ResultWrapperUtils.createResultWrapper(response))
    }
}