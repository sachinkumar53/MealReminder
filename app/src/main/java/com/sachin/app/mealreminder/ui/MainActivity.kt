package com.sachin.app.mealreminder.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sachin.app.mealreminder.databinding.ActivityMainBinding
import com.sachin.app.mealreminder.model.getNow
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.apply {
            offscreenPageLimit = 1
            adapter = PageAdapter(this@MainActivity)
        }


        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getTabTitleByPosition(position)
        }.attach()

        showWish()

        binding.fab.setOnClickListener {
            binding.viewPager.apply {
                if (currentItem < 6)
                    currentItem += 1
                else currentItem = 0
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTabTitleByPosition(position: Int): String = when (position) {
        0 -> "Today's meal"
        1 -> "Tomorrow"
        else -> {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, position)
            SimpleDateFormat("dd MMMM").format(calendar.time).toString()
        }
    }


    @SuppressLint("DefaultLocale")
    private fun showWish() {
        binding.wishTextView.text =
            String.format("Good %s,", getNow().name.toLowerCase().capitalize())
    }

}