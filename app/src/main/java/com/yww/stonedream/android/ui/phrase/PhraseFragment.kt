package com.yww.stonedream.android.ui.phrase

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yww.stonedream.android.MainActivity
import com.yww.stonedream.android.R
import com.yww.stonedream.android.ui.contrast.ContrastActivity
import kotlinx.android.synthetic.main.fragment_phrase.*

class PhraseFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(PhraseViewModel::class.java) }

    private lateinit var adapter: PhraseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phrase, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is MainActivity && viewModel.isPhraseSaved()) {
            val phrase = viewModel.getSavedPlace()
            val intent = Intent(context, ContrastActivity::class.java).apply {
                putExtra("phrase_id", phrase.id)
                putExtra("phrase_name", phrase.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = PhraseAdapter(this, viewModel.phraseList)
        recyclerView.adapter = adapter
        searchPhraseEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPhrases(content)
            } else {
                //viewModel.searchPhrases(content)
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.phraseList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.phraseLiveData.observe(viewLifecycleOwner, Observer { result ->
            val phrases = result.getOrNull()
            if (phrases != null) {
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.phraseList.clear()
                viewModel.phraseList.addAll(phrases)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能检索到任何词条", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}