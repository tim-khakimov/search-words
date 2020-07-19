package com.timkhakimov.searchwords.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timkhakimov.searchwords.R
import com.timkhakimov.searchwords.SearchWordsApp
import com.timkhakimov.searchwords.domain.data.model.Meaning
import com.timkhakimov.searchwords.presentation.SearchWordsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var searchWordsViewModel : SearchWordsViewModel
    private val adapter = MeaningsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSearchWordsViewModel()
        setUpEditQueryHandler()
        setUpList()
        observeData()
    }

    private fun initSearchWordsViewModel() {
        searchWordsViewModel = getViewModel(SearchWordsViewModel::class.java)
        (application as SearchWordsApp).component.inject(searchWordsViewModel)
        searchWordsViewModel.start()
    }

    private fun observeData() {
        searchWordsViewModel.meaningsLiveData.observe(this, Observer { adapter.setMeanings(it) })
    }

    private fun setUpEditQueryHandler() {
        etQuery.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchWordsViewModel.searchWords(etQuery.text.toString())
                Toast.makeText(this@MainActivity, etQuery.text.toString(), Toast.LENGTH_LONG).show()
            }
            true
        }
    }

    private fun setUpList() {
        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter
        adapter.meaningSelectHandler = {openMeaning(it) }
    }

    private fun openMeaning(meaning: Meaning) {

    }

    private class MeaningsAdapter : RecyclerView.Adapter<TextMeaningHolder>() {

        val data = mutableListOf<Meaning>()
        var meaningSelectHandler : (Meaning) -> Unit = {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextMeaningHolder {
            return TextMeaningHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_meaning, parent, false))
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: TextMeaningHolder, position: Int) {
            val meaning = data[position]
            holder.bind(meaning)
            holder.itemView.setOnClickListener { meaningSelectHandler.invoke(meaning) }
        }

        fun setMeanings(meanings : List<Meaning>?) {
            data.clear()
            meanings?.let { data.addAll(it) }
            notifyDataSetChanged()
        }
    }

    private class TextMeaningHolder(root : View) : RecyclerView.ViewHolder(root) {

        val tvMeaning : TextView = root.findViewById(R.id.tvMeaning)

        fun bind(meaning: Meaning) {
            tvMeaning.text = meaning.translation?.text
        }
    }
}