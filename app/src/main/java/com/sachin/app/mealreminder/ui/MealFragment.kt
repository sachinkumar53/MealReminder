package com.sachin.app.mealreminder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sachin.app.mealreminder.database.AppDatabase
import com.sachin.app.mealreminder.databinding.FragmentMealBinding
import kotlinx.coroutines.launch
import java.util.*

class MealFragment(private val position: Int) : Fragment() {
    private var _binding: FragmentMealBinding? = null
    private val binding: FragmentMealBinding
        get() = _binding!!

    private val mealDao by lazy { AppDatabase(requireContext()).mealDao() }
    private val mealAdapter by lazy { MealAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = mealAdapter
        }
        loadMeal()
        return binding.root
    }

    private fun loadMeal() {
        lifecycleScope.launch {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, position)
            val day = calendar[Calendar.DAY_OF_WEEK]
            mealDao.findByDay(day).let {
                mealAdapter.submitList(it.meals, calendar.time)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}