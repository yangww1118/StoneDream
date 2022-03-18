package com.yww.stonedream.android.logic.model

import java.util.*

data class PhraseResponse(val status: String, val phrases: List<Phrase>)

data class Phrase(val id: Int, val name: String, val parent_id: Int, val depth: Int,
                  val children_count: Int, val memo: String, val created_at: Date,
                  val updated_at: Date, val phrase_url: String)