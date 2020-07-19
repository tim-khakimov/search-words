package com.timkhakimov.searchwords.domain.interactors

import com.timkhakimov.searchwords.domain.boundary.OutputBoundary
import com.timkhakimov.searchwords.domain.boundary.ResultWrapper
import com.timkhakimov.searchwords.domain.boundary.Status
import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.domain.data.source.Repository
import com.timkhakimov.searchwords.domain.data.source.Response
import com.timkhakimov.searchwords.domain.interactors.Utils.anyNonNull
import com.timkhakimov.searchwords.domain.interactors.Utils.eqNonNull
import com.timkhakimov.searchwords.domain.interactors.Utils.meaning
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

/**
 * Created by Timur Khakimov on 19.07.2020
 */
@RunWith(JUnit4::class)
class SearchWordsInteractorTest{

    @Mock
    lateinit var repository: Repository
    @Mock
    lateinit var wordsOutputBoundary: OutputBoundary<ResultWrapper<List<Meaning>>>
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()
    lateinit var searchWordsInteractor : SearchWordsInteractor
    private val meanings = meanings()
    private val throwable = Throwable("")

    @Before
    fun setUp() {
        searchWordsInteractor = SearchWordsInteractor(repository, wordsOutputBoundary)
    }

    @Test
    fun checkParameters() {
        searchWordsInteractor.search("query")
        verify(repository).searchWords(eqNonNull("query"), anyNonNull())
        searchWordsInteractor.search("other query")
        verify(repository).searchWords(eqNonNull("other query"), anyNonNull())
    }

    @Test
    fun checkSuccessResult() {
        setUpSuccessResponse()
        searchWordsInteractor.search("query")
        verify(wordsOutputBoundary).sendData(eqNonNull(ResultWrapper(Status.LOADING)))
        verify(repository).searchWords(eqNonNull("query"), anyNonNull())
        verify(wordsOutputBoundary).sendData(ResultWrapper(Status.SUCCESS, meanings))
        verifyNoMoreInteractions(repository)
        verifyNoMoreInteractions(wordsOutputBoundary)
    }

    @Test
    fun checkErrorResult() {
        setUpErrorResponse()
        searchWordsInteractor.search("query")
        verify(wordsOutputBoundary).sendData(eqNonNull(ResultWrapper(Status.LOADING)))
        verify(repository).searchWords(eqNonNull("query"), anyNonNull())
        verify(wordsOutputBoundary).sendData(ResultWrapper(Status.ERROR, null, throwable))
        verifyNoMoreInteractions(repository)
        verifyNoMoreInteractions(wordsOutputBoundary)
    }

    private fun setUpSuccessResponse() {
        doAnswer {
            val callback = it.arguments[1] as (Response<List<Meaning>>) -> Unit
            callback.invoke(Response(meanings, null))
            null
        }.`when`(repository).searchWords(anyNonNull(), anyNonNull())
    }

    private fun setUpErrorResponse() {
        doAnswer {
            val callback = it.arguments[1] as (Response<List<Meaning>>) -> Unit
            callback.invoke(Response(null, throwable))
            null
        }.`when`(repository).searchWords(anyNonNull(), anyNonNull())
    }

    private fun meanings() : List<Meaning> {
        return listOf(meaning(2, "text"))
    }
}