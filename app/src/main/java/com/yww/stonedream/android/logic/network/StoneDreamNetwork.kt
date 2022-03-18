package com.yww.stonedream.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object StoneDreamNetwork {

    private val phraseService = ServiceCreator.create<PhraseService>()
    private val contrastService = ServiceCreator.create(ContrastService::class.java)

    suspend fun searchPhrases(query: String) = phraseService.searchPhrases(query).await()
    suspend fun getDetailContrast(id: Int) = contrastService.getDetailContrast(id).await()
    suspend fun getCompareContrast(comparer_id: Int) = contrastService.getCompareContrast(comparer_id).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation -> enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null) continuation.resume(body)
                else continuation.resumeWithException(RuntimeException("response body is null"))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        }) }
    }
}