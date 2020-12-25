package com.sachin.app.mealreminder.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sachin.app.mealreminder.modal.MealCard
import com.sachin.app.mealreminder.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cards = arrayListOf<MealCard>()
        cards.add(MealCard("Breakfast", R.drawable.ic_breakfast_1, R.color.green, 570))
        cards.add(MealCard("Lunch", R.drawable.ic_omelette, R.color.orange, 810))
        cards.add(MealCard("Breakfast", R.drawable.ic_pizza, R.color.blue, 1140))
        cards.add(MealCard("Dinner", R.drawable.ic_indian_food, R.color.red, 1230))

        main_listview.adapter = CardAdapter(cards)
    }


}