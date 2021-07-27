package com.sachin.app.mealreminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sachin.app.mealreminder.model.WeeklyMeal

@Database(entities = [WeeklyMeal::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private fun buildDataBase(context: Context): AppDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "meal_database.db"
        ).createFromAsset("meal_database.db").build()

        operator fun invoke(context: Context): AppDatabase =
        // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            INSTANCE ?: synchronized(this) {
                buildDataBase(context).also { INSTANCE = it }
            }
    }
}