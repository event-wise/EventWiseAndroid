package com.example.eventwise.helperfunctions

import android.annotation.SuppressLint
import android.os.Build
import java.time.Instant
import java.time.format.DateTimeFormatter

@SuppressLint("SimpleDateFormat")
fun dateTimeCorrectFormat(datetime: String?) : String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && datetime != null) {
        return DateTimeFormatter.ofPattern("HH:mm dd-MM").format(Instant.parse(datetime))
    }
    return ""
}
