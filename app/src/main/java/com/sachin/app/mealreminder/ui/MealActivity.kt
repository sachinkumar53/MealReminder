package com.sachin.app.mealreminder.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager.LayoutParams.*
import androidx.appcompat.app.AppCompatActivity
import com.sachin.app.mealreminder.R

class MealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setupWindow()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_meal)
    }

    private fun setupWindow() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        }

        //window.setDecorFitsSystemWindows(false)
        window.attributes.apply {
            type = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                TYPE_APPLICATION_OVERLAY else TYPE_SYSTEM_OVERLAY

            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O_MR1)
                flags = flags or FLAG_SHOW_WHEN_LOCKED or FLAG_TURN_SCREEN_ON

        }.also { window.attributes = it }

        window?.decorView?.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?) = true
    override fun onBackPressed() {}
}