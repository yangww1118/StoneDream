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
import kotlinx.android.synthetic.main.activity_contrast.*

class PhraseAdapter(private val fragment: PhraseFragment, private val phraseList: List<Phrase>) :
    RecyclerView.Adapter<PhraseAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val phraseName: TextView = view.findViewById(R.id.phraseName)
            val phraseMemo: TextView = view.findViewById(R.id.phraseMemo)
            val phraseParentName: TextView = view.findViewById(R.id.phraseParentName)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.phrase_item, parent, false)

        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val phrase = phraseList[position]
            val activity = fragment.activity
            if (activity is ContrastActivity) {
                activity.drawerLayout.closeDrawers()
                activity.viewModel.phraseId = phrase.id
                activity.viewModel.phraseName = phrase.name
                activity.refreshContrast()
            } else {
                val intent = Intent(parent.context, ContrastActivity::class.java).apply {
                    putExtra("phrase_id", phrase.id)
                    putExtra("phrase_name", phrase.name)
                    //Toast.makeText(holder.itemView.context, "你点击了" + phrase.id + phrase.name, Toast.LENGTH_SHORT).show()
                }
                fragment.startActivity(intent)
                fragment.activity?.finish()
            }
            fragment.viewModel.savePhrase(phrase)
        }

        //return ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phrase = phraseList[position]
        holder.phraseName.text = phrase.name
        holder.phraseMemo.text = phrase.memo
        holder.phraseParentName.text = ">> ${phrase.parent_name}"
    }

    override fun getItemCount() = phraseList.size
}