package com.example.eventwise.helperfunctions

import android.os.Build
import java.text.SimpleDateFormat
import java.util.Locale

fun dateToInstantConverter(dateTimeString: String) : String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.UK).parse(dateTimeString)?.toInstant().toString()
    } else ""
}
