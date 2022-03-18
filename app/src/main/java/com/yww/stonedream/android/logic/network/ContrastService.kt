package com.yww.stonedream.android.logic.network

import com.yww.stonedream.android.logic.model.CompareResponse
import com.yww.stonedream.android.logic.model.DetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ContrastService {

    @GET("api/v1/phrases/{phrase_id}.json")

    fun getDetailContrast(@Path("phrase_id") phrase_id: Int) : Call<DetailResponse>

    @GET("api/v1/phrase/{phrase_id}/compares.json")

    fun getCompareContrast(@Path("phrase_id") phrase_id: Int) : Call<CompareResponse>
}