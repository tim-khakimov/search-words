package com.timkhakimov.searchwords.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timkhakimov.searchwords.domain.boundary.ResultWrapper
import com.timkhakimov.searchwords.domain.boundary.Status
import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.domain.interactors.FindMeaningInteractor
import com.timkhakimov.searchwords.presentation.containers.FoundMeaningsContainer
import com.timkhakimov.searchwords.presentation.containers.MeaningContainer
import javax.inject.Inject

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class MeaningViewModel : ViewModel() {

    @Inject
    lateinit var findMeaningInteractor: FindMeaningInteractor
    @Inject
    lateinit var meaningContainer: MeaningContainer
    val wordLiveData = MutableLiveData<String>()
    val meaningLiveData = MutableLiveData<Meaning>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()

    fun start(word : String, meaningId: Int) {
        wordLiveData.value = word
        findMeaningInteractor.findMeaning(meaningId)
        meaningContainer.observeData { handleResult(it) }
    }

    private fun handleResult(resultWrapper: ResultWrapper<Meaning>) {
        meaningLiveData.value = resultWrapper.data
        loadingLiveData.value = resultWrapper.status == Status.LOADING
        errorLiveData.value = resultWrapper.throwable
    }
}