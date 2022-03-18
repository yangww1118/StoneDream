package com.yww.stonedream.android.logic

import androidx.lifecycle.liveData
import com.yww.stonedream.android.logic.model.Contrast
import com.yww.stonedream.android.logic.model.Phrase
import com.yww.stonedream.android.logic.network.StoneDreamNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPhrases(query: String) = fire(Dispatchers.IO) {
        val phraseResponse = StoneDreamNetwork.searchPhrases(query)
        if (phraseResponse.status == "ok") {
            val phrases = phraseResponse.phrases
            Result.success(phrases)
        } else {
            Result.failure(RuntimeException("response status is ${phraseResponse.status}"))
        }
    }

    fun refreshContrast(phrase_id: Int) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredDetail = async {
                StoneDreamNetwork.getDetailContrast(phrase_id)
            }
            val deferredCompare = async {
                StoneDreamNetwork.getCompareContrast(phrase_id)
            }
            val detailResponse = deferredDetail.await()
            val compareResponse = deferredCompare.await()

            if (detailResponse.status == "ok" && compareResponse.status == "ok") {
                val contrast = Contrast(detailResponse.phrase, compareResponse.compare)
                Result.success(contrast)
            } else {
                Result.failure(
                    RuntimeException(
                        "detail response status is ${detailResponse.status}" +
                         "compare response status is ${compareResponse.status}"
                    )
                )
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}