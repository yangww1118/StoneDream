package com.yww.stonedream.android.ui.contrast

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yww.stonedream.android.logic.Repository

class ContrastViewModel : ViewModel() {

    private val phraseLiveData = MutableLiveData<Int>()

    var phraseId = 1

    var phraseName = ""

    val contrastLiveData = Transformations.switchMap(phraseLiveData) {
        phrase_id -> Repository.refreshContrast(phrase_id)
    }

    fun refreshContrast(phrase_id: Int) {
        phraseLiveData.value = phrase_id
    }
}