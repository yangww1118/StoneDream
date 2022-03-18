package com.yww.stonedream.android.ui.contrast

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yww.stonedream.android.R
import com.yww.stonedream.android.logic.model.Contrast
import com.yww.stonedream.android.logic.model.getVersion
import kotlinx.android.synthetic.main.activity_contrast.*
import kotlinx.android.synthetic.main.compare.*
import kotlinx.android.synthetic.main.compare_item.*
import kotlinx.android.synthetic.main.detail.*
import kotlinx.android.synthetic.main.info.*

class ContrastActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProvider(this).get(ContrastViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT

        setContentView(R.layout.activity_contrast)

        if (viewModel.phraseId == 1) {
            viewModel.phraseId = intent.getIntExtra("phrase_id", -1) ?: 1
        }

        if (viewModel.phraseName.isEmpty()) {
            viewModel.phraseName = intent.getStringExtra("phrase_name") ?: ""
        }

        //Log.d("params", "extra phrase_id is " + viewModel.phraseId)
        //Log.d("params", "extra phrase_name is " + viewModel.phraseName)

        viewModel.contrastLiveData.observe(this, Observer { result ->
            val contrast = result.getOrNull()
            if (contrast != null) {
                showContrastInfo(contrast)
                //Log.d("params", "extra phrase_name is " + contrast)
            } else {
                Toast.makeText(this, "无法成功获取词条信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        viewModel.refreshContrast(viewModel.phraseId)
    }

    private fun showContrastInfo(contrast: Contrast) {
        phraseName.text = viewModel.phraseName

        val phrase = contrast.phrase
        val compare = contrast.compare

        //Log.d("layout", "show the layout " + phraseName.text)

        phraseParent.text = phrase.phrase_parent
        phraseGrandpa.text = phrase.phrase_grandpa
        phraseMemo.text = phrase.memo

        detailLayout.setBackgroundResource(getVersion(phrase.phrase_grandpa).bg)

        compareLayout.removeAllViews()

        val counts = compare.size
        //Log.d("layout", "show the compare count " + counts)
        for (i in 0 until counts) {
            val vercon = compare[i].comparing_parent
            val view = LayoutInflater.from(this).inflate(R.layout.compare_item, compareLayout, false)
            val compareName = view.findViewById(R.id.compareName) as TextView
            val verIcon = view.findViewById(R.id.versionIcon) as ImageView
            val verInfo = view.findViewById(R.id.compareGrandpa) as TextView

            compareName.text = compare[i].comparing

            val ver = getVersion(vercon)
            verIcon.setImageResource(ver.icon)
            verInfo.text = ver.info

            compareLayout.addView(view)
            //Log.d("layout", "show the compare " + i + verInfo.text)
        }

        comparingCount.text = phrase.parent_id.toString()
        comparerCount.text = phrase.depth.toString()
        phraseID.text = phrase.id.toString()
        childrenCount.text = phrase.children_count.toString()
        contrastLayout.visibility = View.VISIBLE
    }
}