package com.timkhakimov.searchwords.data

import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.domain.data.model.Word

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class LocalMeaningsStore {

    private val meaningsMap = hashMapOf<Int, Meaning>()

    fun saveWords(words: List<Word>) {
        for (word in words) {
            word.meanings?.let { saveMeanings(it) }
        }
    }

    private fun saveMeanings(meanings: List<Meaning>) {
        for (meaning in meanings) {
            meaningsMap.put(meaning.id, meaning)
        }
    }

    fun getMeaning(id: Int): Meaning? {
        return meaningsMap[id]
    }
}