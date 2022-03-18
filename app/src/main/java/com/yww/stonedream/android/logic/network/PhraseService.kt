package com.yww.stonedream.android.logic.network

import com.yww.stonedream.android.logic.model.PhraseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhraseService {

    @GET("api/v1/phrases")

    fun searchPhrases(@Query("query") query: String) : Call<PhraseResponse>
}