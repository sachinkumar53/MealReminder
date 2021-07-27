package com.sachin.app.mealreminder.ui

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sachin.app.mealreminder.R
import com.sachin.app.mealreminder.databinding.HeaderBinding
import com.sachin.app.mealreminder.databinding.MealItemBinding
import com.sachin.app.mealreminder.model.Meal
import com.sachin.app.mealreminder.model.Now
import com.sachin.app.mealreminder.model.getNow
import java.util.*

class MealAdapter : RecyclerView.Adapter<MealAdapter.CardViewHolder>() {
    private val itemList = arrayListOf<Meal>()

    override fun getItemCount() = itemList.size
    private var isHeaderEnabled = false

    fun submitList(items: List<Meal>, date: Date) {
        itemList.clear()
        itemList.addAll(items)

        isHeaderEnabled = DateUtils.isToday(date.time)
        if (isHeaderEnabled) {
            when (getNow()) {
                Now.MORNING -> {
                }
                Now.AFTERNOON -> {
                    val lunch = itemList[1]
                    itemList.remove(lunch)
                    itemList.add(0, lunch)
                }

                Now.EVENING -> {
                    val snacks = itemList[2]
                    itemList.remove(snacks)
                    itemList.add(0, snacks)
                }

                Now.NIGHT -> {
                    val dinner = itemList[3]
                    itemList.remove(dinner)
                    itemList.add(0, dinner)
                }
            }
            itemList.add(0, Meal("dummy", listOf()))
            itemList.add(2, Meal("dummy", listOf()))
        }

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) =
        if (isHeaderEnabled && (position == 0 || position == 2)) 1 else 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewHolder(
        if (viewType == 1)
            HeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        else
            MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bindTo(itemList[position])

    inner class CardViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(meal: Meal) {

            if (getItemViewType(adapterPosition) == 0) {
                with(binding as MealItemBinding) {
                    val context = root.context

                    mealName.text = meal.name
                    meal.items.forEach {
                        val textView = TextView(context)
                        textView.text = context.getString(R.string.menu_item_format, it)
                        textView.textSize = 17f
                        textView.setTextColor(context.getColor(R.color.white))
                        binding.itemsLayout.addView(textView)
                    }

                    binding.background.setImageResource(getBackgroundByMeal(meal))
                }
            } else {
                with(binding as HeaderBinding) {
                    heading.text =
                        if (adapterPosition == 0)
                            root.context.getString(R.string.now)
                        else root.context.getString(R.string.other)
                }
            }
        }

        private fun getBackgroundByMeal(meal: Meal): Int = when (meal.name) {
            "Breakfast" -> R.drawable.gradient_background_1
            "Lunch" -> R.drawable.gradient_background_2
            "Evening snacks" -> R.drawable.gradient_background_3
            "Dinner" -> R.drawable.gradient_background_4
            else -> R.drawable.gradient_background_1
        }
    }

}

