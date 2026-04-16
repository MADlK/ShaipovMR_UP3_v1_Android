package com.bignerdranch.android.ShaipovM_v8

import android.content.Context
import kotlinx.coroutines.flow.Flow

class smth (private val context: Context){
    private val dao_ed=AppDatabase.getInstance(context).dao_ed()

    fun getAllProducts(): Flow<List<food_menu>> {
        return dao_ed.getAllProducts()
    }

    suspend fun addProduct(name: String) {
        val product = food_menu(
            id = 0,
            difficultyName = name
        )
        dao_ed.InsertProduct(product)
    }

    suspend fun deleteProduct(product: food_menu) {
        dao_ed.deleteProduct(product)
    }

}
