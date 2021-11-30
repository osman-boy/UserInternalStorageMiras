package com.osmanboy.oppoproducts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Osman Boy on 11/28/2021.
 **/

@Entity(tableName = "oppo_products")
class Oppo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String="",
    val description: String="",
    val price: Int=0,
    val imageUri:String=""
)




