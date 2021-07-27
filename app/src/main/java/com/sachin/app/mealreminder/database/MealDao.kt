package com.sachin.app.mealreminder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sachin.app.mealreminder.model.WeeklyMeal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM weekly_meal")
    fun getAllMeals(): Flow<List<WeeklyMeal>>

    @Insert
    suspend fun insertAll(vararg weeklyMeals: WeeklyMeal)

    @Query("SELECT * FROM weekly_meal WHERE day = :day")
    suspend fun findByDay(day: Int): WeeklyMeal

    @Update
    suspend fun update(weeklyMeal: WeeklyMeal)


    /*@Delete
    suspend fun delete(meal: Meal)*/

/*
    @Query("DELETE FROM meal")
    suspend fun deleteAll()
*/

}