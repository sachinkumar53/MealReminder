package com.sachin.app.mealreminder.ui

import android.content.Context
import android.graphics.Paint
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin.app.mealreminder.modal.MealCard
import com.sachin.app.mealreminder.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.meal_card_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class CardAdapter(private val mealCardList: List<MealCard>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun getItemCount() = mealCardList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.meal_card_item, parent, false)
    )


    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bindTo(mealCardList[position])

    inner class CardViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindTo(mealCard: MealCard) {
            with(containerView) {
                card_view.setCardBackgroundColor(context.getColor(mealCard.color))
                meal_icon.setImageResource(mealCard.icon)
                meal_name.text = mealCard.title
                meal_time.text = mealCard.time.toTimeString(context)

                if (mealCard.eaten) {
                    eat_status.apply {
                        text = "Eaten"
                        alpha = 0.4F
                        isEnabled = false
                    }
                } else {
                    eat_status.apply {
                        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
                        text = "Eat now"
                        alpha = 1F
                        isEnabled = true
                    }
                }
            }
        }
    }
}

private fun Int.toTimeString(context: Context): CharSequence {
    val is24Hr = DateFormat.is24HourFormat(context)
    val hour = this / 60
    val minute = this % 60

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, minute)
    calendar.set(Calendar.SECOND, 0)

    return if (is24Hr) SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.timeInMillis)
    else SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(calendar.timeInMillis)
}
