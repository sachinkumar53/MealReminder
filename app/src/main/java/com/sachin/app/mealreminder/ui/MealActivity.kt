package com.sachin.app.mealreminder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.sachin.app.mealreminder.R

class MealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?) = true
    override fun onBackPressed() {}
}