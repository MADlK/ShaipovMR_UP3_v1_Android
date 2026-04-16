package com.bignerdranch.android.ShaipovM_v8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class menu_screen : AppCompatActivity() {
    private lateinit var dishes: LinearLayout
    private lateinit var zakazButton: AppCompatButton

    private val menuDishes = listOf(
        " Пицца Маргарита - 450 руб",
        " Паста Карбонара - 380 руб",
        " Салат Цезарь - 320 руб",
        " Бургер Классический - 290 руб",
        " Суши Филадельфия - 520 руб",
        " Стейк Рибай - 890 руб",
        " Тирамису - 280 руб",
        " Капучино - 180 руб",
        " Мороженое Ванильное - 150 руб",
        " Кока-Кола - 120 руб"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_screen)

        dishes = findViewById(R.id.dishesContainer)
        zakazButton = findViewById(R.id.zakaz)

        for (dish in menuDishes) {
            val dishLayout = LinearLayout(this)
            dishLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dishLayout.orientation = LinearLayout.HORIZONTAL
            dishLayout.setPadding(0, 8, 0, 8)

            // Текст с названием блюда
            val textView = TextView(this)
            textView.layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f // вес
            )
            textView.text = dish
            textView.textSize = 16f
            textView.setTextColor(getColor(android.R.color.white))

            // Кнопка "Добавить"
            val addButton = Button(this)
            addButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            addButton.text = "Добавить"
            addButton.setBackgroundColor(getColor(android.R.color.holo_green_dark))
            addButton.setTextColor(getColor(android.R.color.white))

            // Обработчик нажатия на кнопку
            addButton.setOnClickListener {
                addDishToMyList(dish)
            }

            // Добавляем элементы в контейнер
            dishLayout.addView(textView)
            dishLayout.addView(addButton)

            // Добавляем контейнер в основной layout
            dishes.addView(dishLayout)
        }
        zakazButton.setOnClickListener {
            println("*****************************************  не какашка")

            // Переход на экран с списком заказа
            val intent = android.content.Intent(this, ResultActivity::class.java)
            startActivity(intent)

        }

    }

    private fun addDishToMyList(dishName: String) {
        // Сохраняем в SharedPreferences
        val shared = getSharedPreferences("share", MODE_PRIVATE)

        // Получаем текущий список блюд
        val currentDishes = shared.getString("selected_dishes", "") ?: ""
        val dishesList = if (currentDishes.isNotEmpty()) {
            currentDishes.split(",").toMutableList()
        } else {
            mutableListOf()
        }

        // Добавляем новое блюдо
        dishesList.add(dishName)

        // Сохраняем обновленный список
        val editor = shared.edit()
        editor.putString("selected_dishes", dishesList.joinToString(","))
        editor.apply()

        // Показываем уведомление
        Toast.makeText(this, "Добавлено: $dishName", Toast.LENGTH_SHORT).show()
    }
}