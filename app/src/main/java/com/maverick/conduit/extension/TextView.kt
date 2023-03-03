package com.maverick.conduit.extension

import android.icu.text.SimpleDateFormat
import android.widget.TextView
import java.util.*

val isDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
val appDateFormat = SimpleDateFormat("MMMM dd,yyyy", Locale.getDefault())

//fun TextView.showFormattedDate(timestamp: String) {
//    val date = isDateFormat.parse(timestamp)
//    text = appDateFormat.format(date)
//}

var TextView.timestamp: String
    set(value) {
        val date = isDateFormat.parse(value)
        text = appDateFormat.format(date)
    }
    get() {
        val date = appDateFormat.parse(text.toString())
        return isDateFormat.format(date)
    }
