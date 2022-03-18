package com.yww.stonedream.android.ui.phrase

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.yww.stonedream.android.R
import com.yww.stonedream.android.logic.model.Phrase
import com.yww.stonedream.android.ui.contrast.ContrastActivity

class PhraseAdapter(private val fragment: Fragment, private val phraseList: List<Phrase>) :
    RecyclerView.Adapter<PhraseAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val phraseName: TextView = view.findViewById(R.id.phraseName)
            val phraseMemo: TextView = view.findViewById(R.id.phraseMemo)
            val phraseUrl: TextView = view.findViewById(R.id.phraseUrl)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.phrase_item, parent, false)

        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val phrase = phraseList[position]
            val intent = Intent(parent.context, ContrastActivity::class.java).apply {
                putExtra("phrase_id", phrase.id)
                putExtra("phrase_name", phrase.name)
                //Toast.makeText(holder.itemView.context, "你点击了" + phrase.id + phrase.name, Toast.LENGTH_SHORT).show()
            }
            fragment.startActivity(intent)
            fragment.activity?.finish()
        }

        //return ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phrase = phraseList[position]
        holder.phraseName.text = phrase.name
        holder.phraseMemo.text = phrase.memo
        holder.phraseUrl.text = phrase.phrase_url
    }

    override fun getItemCount() = phraseList.size
}