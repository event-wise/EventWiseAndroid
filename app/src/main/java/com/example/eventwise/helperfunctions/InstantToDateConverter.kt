package com.example.eventwise.helperfunctions

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@SuppressLint("SimpleDateFormat")
fun instantToDateConverter(instantString: String) : String {
    try {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            SimpleDateFormat("HH:mm dd-MM-yyyy").format(
                Date.from(Instant.parse(instantString)
                )
            )
        } else ""
    } catch (e: java.lang.Exception){
        return ""
    }
}
