package com.osmanboy.oppoproducts.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

/**
 * Created by Osman Boy on 11/28/2021.
 **/
@Dao
interface OPPODAO {


    @Query("SELECT* FROM oppo_products WHERE id=:oppoId LIMIT 1")
    suspend fun getItem(oppoId:Int):Oppo

    @Insert(onConflict = IGNORE)
    suspend fun addItem(item : Oppo)

    @Query("DELETE FROM oppo_products WHERE id=:oppoId")
    suspend fun deleteItem(oppoId: Int)

    @Query("SELECT * FROM oppo_products")
    suspend fun getList():List<Oppo>
}