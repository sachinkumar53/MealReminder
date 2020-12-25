package com.sachin.app.mealreminder.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sachin.app.mealreminder.ui.MealActivity

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity(Intent(context, MealActivity::class.java))
    }
}