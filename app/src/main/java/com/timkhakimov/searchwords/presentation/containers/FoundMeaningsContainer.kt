package com.timkhakimov.searchwords.presentation.containers

import com.timkhakimov.searchwords.domain.boundary.OutputBoundary
import com.timkhakimov.searchwords.domain.boundary.ResultWrapper
import com.timkhakimov.searchwords.domain.data.model.Meaning
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class FoundMeaningsContainer: OutputBoundary<ResultWrapper<List<Meaning>>>, Container<ResultWrapper<List<Meaning>>> {

    val resultMeaningsSubject : BehaviorSubject<ResultWrapper<List<Meaning>>>

    init {
        resultMeaningsSubject = BehaviorSubject.create()
        resultMeaningsSubject.observeOn(AndroidSchedulers.mainThread())
    }

    override fun sendData(data: ResultWrapper<List<Meaning>>) {
        resultMeaningsSubject.onNext(data)
    }

    override fun observeData(observer: (ResultWrapper<List<Meaning>>) -> Unit) {
        resultMeaningsSubject.subscribe { observer.invoke(it) }
    }
}