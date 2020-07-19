package com.timkhakimov.searchwords.data

import com.timkhakimov.searchwords.data.Hardcode.EMPTY_RESPONSE_BODY
import com.timkhakimov.searchwords.domain.data.model.Word
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class RemoteDataSource(private val restApi: RestApi) {

    var disposable: Disposable? = null

    fun searchWords(
        query: String,
        successCallback: (List<Word>) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        disposable?.dispose()
        disposable = restApi.searchWords(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.body()?.let(successCallback)
                    ?: errorCallback.invoke(Throwable(EMPTY_RESPONSE_BODY))
                disposable = null
            }, {
                errorCallback.invoke(it)
                disposable = null
            })
    }
}