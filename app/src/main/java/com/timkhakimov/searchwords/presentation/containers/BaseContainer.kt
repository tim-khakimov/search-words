package com.timkhakimov.searchwords.presentation.containers

import com.timkhakimov.searchwords.domain.boundary.OutputBoundary
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by Timur Khakimov on 19.07.2020
 */
abstract class BaseContainer<T> : Container<T>, OutputBoundary<T> {

    val subject : BehaviorSubject<T>

    init {
        subject = BehaviorSubject.create()
    }

    override fun observeData(observer: (T) -> Unit) {
        subject.subscribe{ observer.invoke(it) }
    }

    override fun sendData(data: T) {
        subject.onNext(data)
    }
}