package com.yww.stonedream.android.logic.model

import com.yww.stonedream.android.R

class Version(val info: String, val icon: Int, val bg: Int)

private val version = mapOf(
    "甲戌本" to Version("甲", R.drawable.ic_clear_day, R.drawable.bg_clear_day),
    "庚辰本" to Version("庚", R.drawable.ic_cloudy, R.drawable.bg_cloudy),
    "己卯本" to Version("己", R.drawable.ic_partly_cloud_day, R.drawable.bg_partly_cloudy_day),
    "列藏本" to Version("列", R.drawable.ic_partly_cloud_night, R.drawable.bg_partly_cloudy_night),
    "蒙府本" to Version("蒙", R.drawable.ic_fog, R.drawable.bg_fog),
    "卞藏本" to Version("卞", R.drawable.ic_clear_night, R.drawable.bg_clear_night),
    "甲辰本" to Version("覺", R.drawable.ic_heavy_rain, R.drawable.bg_rain),
    "舒序本" to Version("舒", R.drawable.ic_hail, R.drawable.bg_snow),
    "夢稿本" to Version("夢", R.drawable.ic_storm_rain, R.drawable.bg_rain),
    "戚大本" to Version("大", R.drawable.ic_heavy_snow, R.drawable.bg_snow),
    "戚小本" to Version("小", R.drawable.ic_light_snow, R.drawable.bg_snow),
    "程甲本" to Version("甲", R.drawable.ic_heavy_haze, R.drawable.bg_fog),
    "程乙本" to Version("乙", R.drawable.ic_light_haze, R.drawable.bg_fog),
    "戚宁本" to Version("宁", R.drawable.ic_light_rain, R.drawable.bg_rain),
    "戚沪本" to Version("沪", R.drawable.ic_sleet, R.drawable.bg_rain),
    "鄭藏本" to Version("藏", R.drawable.ic_moderate_rain, R.drawable.bg_rain),
    "靖佚本" to Version("靖", R.drawable.ic_moderate_snow, R.drawable.bg_snow)
)

fun getVersion(vercon: String) : Version {
    return version[vercon] ?: version["甲戌本"]!!
}