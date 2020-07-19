package com.timkhakimov.searchwords.domain.interactors

import com.timkhakimov.searchwords.domain.boundary.OutputBoundary
import com.timkhakimov.searchwords.domain.boundary.ResultWrapper
import com.timkhakimov.searchwords.domain.boundary.Status
import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.domain.data.source.Repository
import com.timkhakimov.searchwords.domain.data.source.Response
import com.timkhakimov.searchwords.domain.interactors.Utils.anyNonNull
import com.timkhakimov.searchwords.domain.interactors.Utils.eqNonNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnit

/**
 * Created by Timur Khakimov on 19.07.2020
 */
@RunWith(JUnit4::class)
class FindMeaningInteractorTest{

    @Mock
    lateinit var repository: Repository
    @Mock
    lateinit var meaningOutputBoundary: OutputBoundary<ResultWrapper<Meaning>>
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()
    lateinit var findMeaningInteractor: FindMeaningInteractor
    private val meaning : Meaning = Utils.meaning(1, "")
    private val throwable = Throwable("")

    @Before
    fun setUp() {
        findMeaningInteractor = FindMeaningInteractor(repository, meaningOutputBoundary)
    }

    @Test
    fun checkParameters() {
        findMeaningInteractor.findMeaning(1)
        verify(repository).getMeaning(eqNonNull(1), anyNonNull())
        findMeaningInteractor.findMeaning(2)
        verify(repository).getMeaning(eqNonNull(2), anyNonNull())
        findMeaningInteractor.findMeaning(0)
        verify(repository).getMeaning(eqNonNull(0), anyNonNull())
    }

    @Test
    fun checkSuccessResult() {
        setUpSuccessResponse()
        findMeaningInteractor.findMeaning(1)
        verify(meaningOutputBoundary).sendData(eqNonNull(ResultWrapper(Status.LOADING)))
        verify(repository).getMeaning(eqNonNull(1), anyNonNull())
        verify(meaningOutputBoundary).sendData(ResultWrapper(Status.SUCCESS, meaning))
        verifyNoMoreInteractions(repository)
        verifyNoMoreInteractions(meaningOutputBoundary)
    }

    @Test
    fun checkErrorResult() {
        setUpErrorResponse()
        findMeaningInteractor.findMeaning(1)
        verify(meaningOutputBoundary).sendData(eqNonNull(ResultWrapper(Status.LOADING)))
        verify(repository).getMeaning(eqNonNull(1), anyNonNull())
        verify(meaningOutputBoundary).sendData(ResultWrapper(Status.ERROR, null, throwable))
        verifyNoMoreInteractions(repository)
        verifyNoMoreInteractions(meaningOutputBoundary)
    }

    private fun setUpSuccessResponse() {
        Mockito.doAnswer {
            val callback = it.arguments[1] as (Response<Meaning>) -> Unit
            callback.invoke(Response(meaning, null))
            null
        }.`when`(repository).getMeaning(anyInt(), anyNonNull())
    }

    private fun setUpErrorResponse() {
        Mockito.doAnswer {
            val callback = it.arguments[1] as (Response<Meaning>) -> Unit
            callback.invoke(Response(null, throwable))
            null
        }.`when`(repository).getMeaning(anyInt(), anyNonNull())
    }
}