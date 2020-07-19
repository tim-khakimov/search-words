package com.timkhakimov.searchwords.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timkhakimov.searchwords.domain.boundary.ResultWrapper
import com.timkhakimov.searchwords.domain.boundary.Status
import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.domain.interactors.SearchWordsInteractor
import com.timkhakimov.searchwords.presentation.containers.FoundMeaningsContainer
import javax.inject.Inject

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class SearchWordsViewModel : ViewModel() {

    @Inject
    lateinit var foundMeaningsContainer: FoundMeaningsContainer
    @Inject
    lateinit var searchWordsInteractor: SearchWordsInteractor
    val meaningsLiveData = MutableLiveData<List<Meaning>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()

    fun start() {
        foundMeaningsContainer.observeData { handleResult(it) }
    }

    private fun handleResult(resultWrapper: ResultWrapper<List<Meaning>>) {
        meaningsLiveData.value = resultWrapper.data
        loadingLiveData.value = resultWrapper.status == Status.LOADING
        errorLiveData.value = resultWrapper.throwable
    }

    fun searchWords(query : String) {
        Log.d("SearchWordsViewModel", "searchWords " + query)
        searchWordsInteractor.search(query)
    }
}