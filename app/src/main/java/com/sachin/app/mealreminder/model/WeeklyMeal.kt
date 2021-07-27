package com.sachin.app.mealreminder.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "weekly_meal")
data class WeeklyMeal(
    val day: Int,

    val meals: List<Meal>,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
