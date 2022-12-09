package com.example.eventwise.helperfunctions

import java.text.SimpleDateFormat
import java.util.Locale

fun dateToInstantConverter(dateTimeString: String) : String {
    return SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.UK).parse(dateTimeString)?.toInstant().toString()
}
