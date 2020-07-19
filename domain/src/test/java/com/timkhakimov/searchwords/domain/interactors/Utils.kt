package com.timkhakimov.searchwords.domain.interactors

import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.domain.data.model.Translation
import com.timkhakimov.searchwords.domain.data.model.Word
import org.mockito.Mockito

/**
 * Created by Timur Khakimov on 19.07.2020
 * Конвертация методов из Mockito для использования в kotlin
 */
object Utils {

    fun <T> anyNonNull(): T = Mockito.any<T>()

    fun <T> eqNonNull(value : T) : T = Mockito.eq(value)

    fun word(id : Int, vararg meanings: Meaning) : Word {
        val word = Word()
        word.id = id
        word.meanings = mutableListOf<Meaning>().also { it.addAll(meanings) }
        return word
    }

    fun meaning(id : Int, text : String) : Meaning {
        val meaning = Meaning()
        meaning.id = id
        meaning.translation = Translation().also { it.text = text }
        return meaning
    }
}