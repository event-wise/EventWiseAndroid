package com.example.eventwise.helperfunctions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@SuppressLint("SimpleDateFormat")
fun instantToDateConverter(instantString: String) : String {
    return try {
        SimpleDateFormat("HH:mm dd-MM-yyyy").format(
                Date.from(Instant.parse(instantString)
            )
        )
    } catch (e: java.lang.Exception){
        ""
    }
}
