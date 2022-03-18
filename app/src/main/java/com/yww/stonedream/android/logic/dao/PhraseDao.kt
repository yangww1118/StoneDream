package com.yww.stonedream.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.yww.stonedream.android.StoneDreamApplication
import com.yww.stonedream.android.logic.model.Phrase

object PhraseDao {

    fun savePhrase(phrase: Phrase) {
        sharedPreferences().edit {
            putString("phrase", Gson().toJson(phrase))
        }
    }

    fun getSavedPhrase(): Phrase {
        val phraseJson = sharedPreferences().getString("phrase", "")
        return Gson().fromJson(phraseJson, Phrase::class.java)
    }

    fun isPhraseSaved() = sharedPreferences().contains("phrase")

    private fun sharedPreferences() = StoneDreamApplication.context.getSharedPreferences("stone_dream",
                                        Context.MODE_PRIVATE)
}