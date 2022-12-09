package com.example.eventwise.helperfunctions

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

fun dateToInstantConverter(dateTimeString: String) : String {
    return try {
        SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.UK).parse(dateTimeString)?.toInstant()
            .toString()
    } catch (e: Exception) {
        Log.e("DateTimeConverter", e.toString())
        ""
    }
}
