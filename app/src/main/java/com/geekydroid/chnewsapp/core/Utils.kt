package com.geekydroid.chnewsapp.core

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    fun parseDateString(dateTimeStr: String): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val date = inputFormatter.parse(dateTimeStr)
        return date?.let { SimpleDateFormat("dd MMM,yyyy", Locale.ENGLISH).format(it) } ?: ""
    }
}