package com.yww.stonedream.android.ui.phrase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yww.stonedream.android.logic.Repository
import com.yww.stonedream.android.logic.dao.PhraseDao
import com.yww.stonedream.android.logic.model.Phrase

class PhraseViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val phraseList = ArrayList<Phrase>()

    val phraseLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPhrases(query)
    }

    fun searchPhrases(query: String) {
        searchLiveData.value = query
    }

    fun savePhrase(phrase: Phrase) = PhraseDao.savePhrase(phrase)

    fun getSavedPlace() = PhraseDao.getSavedPhrase()

    fun isPhraseSaved() = PhraseDao.isPhraseSaved()
}