package com.yww.stonedream.android.logic.model

import java.util.*

data class DetailResponse(val status: String, val phrase: Phrase) {

     data class Phrase(val id: Int, val name: String, val parent_id: Int,
                       val phrase_grandpa: String, val phrase_parent: String,
                       val depth: Int, val children_count: Int, val memo: String,
                       val created_at: Date, val updated_at: Date)
}