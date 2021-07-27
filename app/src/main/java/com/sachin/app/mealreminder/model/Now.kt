package com.sachin.app.mealreminder.model

import java.util.*

enum class Now {
    MORNING,
    AFTERNOON,
    EVENING,
    NIGHT
}

fun getNow(): Now {
    val calendar = Calendar.getInstance()
    return when (calendar[Calendar.HOUR_OF_DAY]) {
        in 0..11 -> Now.MORNING
        in 12..15 -> Now.AFTERNOON
        in 16..20 -> Now.EVENING
        in 21..23 -> Now.NIGHT
        else -> Now.MORNING
    }
}
