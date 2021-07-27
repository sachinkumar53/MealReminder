package com.sachin.app.mealreminder.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sachin.app.mealreminder.model.Meal


class Converters {

    @TypeConverter
    fun fromMealJson(mealJson: String): List<Meal> =
        Gson().fromJson(mealJson, Array<Meal>::class.java).toList()

    @TypeConverter
    fun toMealJson(mealList: List<Meal>): String = Gson().toJson(mealList)
}