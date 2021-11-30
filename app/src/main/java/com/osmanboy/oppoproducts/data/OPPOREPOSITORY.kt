package com.osmanboy.oppoproducts.data

import android.app.Application
import androidx.lifecycle.LiveData

/**
 * Created by Osman Boy on 11/28/2021.
 **/
class OPPOREPOSITORY(private val application: Application) {
    private val oppodao: OPPODAO = OPPODATABASE.getInstance(application).shopListDao()

    suspend fun addShopItem(oppoItem: Oppo) = oppodao.addItem(oppoItem)


    suspend fun deleteShopItem(deleteItem: Oppo) = oppodao.deleteItem(deleteItem.id)


    suspend fun editShopItem(editItem: Oppo) = oppodao.addItem(editItem)


    suspend fun getShopItem(shopId: Int): Oppo = oppodao.getItem(shopId)


    suspend fun getShopList(): List<Oppo> = oppodao.getList()

}