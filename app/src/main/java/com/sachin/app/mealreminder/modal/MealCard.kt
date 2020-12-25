package com.sachin.app.mealreminder.modal

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class MealCard(
    val title: String,

    @DrawableRes
    val icon: Int,

    @ColorRes
    val color: Int,

    val time: Int,
    val eaten: Boolean = false
)
