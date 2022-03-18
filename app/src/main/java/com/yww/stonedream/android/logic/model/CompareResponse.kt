package com.yww.stonedream.android.logic.model

data class CompareResponse(val status: String, val comparer_parent: String, val comparer: String,
                            val compare: List<Compare>) {

    data class Compare(val id: Int, val comparing_parent: String, val comparing: String)
}
