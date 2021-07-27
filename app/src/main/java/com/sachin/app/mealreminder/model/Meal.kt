package com.sachin.app.mealreminder.model

import androidx.annotation.Keep

@Keep
data class Meal(
    val name: String,
    val items: List<String>
)