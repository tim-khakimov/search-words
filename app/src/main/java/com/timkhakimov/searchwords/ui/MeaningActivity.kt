package com.timkhakimov.searchwords.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.timkhakimov.searchwords.R
import com.timkhakimov.searchwords.SearchWordsApp
import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.presentation.MeaningViewModel
import kotlinx.android.synthetic.main.activity_meaning.*

/**
 * Created by Timur Khakimov on 19.07.2020
 */
class MeaningActivity : BaseActivity() {

    lateinit var meaningViewModel : MeaningViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meaning)
        initMeaningsViewModel()
        start()
        observeData()
    }

    private fun initMeaningsViewModel() {
        meaningViewModel = getViewModel(MeaningViewModel::class.java)
        (application as SearchWordsApp).component.inject(meaningViewModel)
    }

    private fun start() {
        val word = intent.getStringExtra(ARG_WORD)
        val meaningId = intent.getIntExtra(ARG_MEANING_ID, 0)
        if(word != null && meaningId > 0) {
            meaningViewModel.start(word, meaningId)
        }
    }

    private fun observeData() {
        meaningViewModel.wordLiveData.observe(this, Observer { tvWord.text = it })
        meaningViewModel.meaningLiveData.observe(this, Observer { meaning ->
            meaning?.let { setMeaning(it) }
        })
    }

    private fun setMeaning(meaning : Meaning) {
        tvMeaning.text = meaning.translation?.text
        Picasso.get()
            .load("https://" + meaning.imageUrl)
            .into(ivImage)
    }

    companion object {
        const val ARG_WORD = "arg_word"
        const val ARG_MEANING_ID = "arg_meaning_id"
    }
}