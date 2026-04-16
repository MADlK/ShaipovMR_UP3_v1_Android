package com.bignerdranch.android.ShaipovM_v8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var repository: smth
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        repository = smth(this)

        textView = findViewById(R.id.vivod)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val shared = getSharedPreferences("share", MODE_PRIVATE)

        val login = shared.getString("login", "")
        val password = shared.getString("password", "")
        val service = shared.getString("service", "")

        textView.text = "ваши данные:" + login + " " + password + " " + service

        val dishesString = shared.getString("selected_dishes", "") ?: ""
        val selectedDishes = if (dishesString.isNotEmpty()) {
            dishesString.split(",")
        } else {
            emptyList()
        }

        val foodMenuList = selectedDishes.mapIndexed { index, dish ->
            food_menu(id = index.toLong(), difficultyName = dish)
        }

        adapter = Adapter(foodMenuList) { product ->
            lifecycleScope.launch {
                repository.deleteProduct(product)
                removeDishFromSharedPrefs(product.difficultyName)

            }



        }

        recyclerView.adapter = adapter
    }

    private fun removeDishFromSharedPrefs(dishName: String) {
        val shared = getSharedPreferences("share", MODE_PRIVATE)
        val currentDishes = shared.getString("selected_dishes", "") ?: ""
        val dishesList = if (currentDishes.isNotEmpty()) {
            currentDishes.split(",").toMutableList()
        } else {
            mutableListOf()
        }

        dishesList.remove(dishName)

        val editor = shared.edit()
        editor.putString("selected_dishes", dishesList.joinToString(","))
        editor.apply()

        recreate()
    }


}