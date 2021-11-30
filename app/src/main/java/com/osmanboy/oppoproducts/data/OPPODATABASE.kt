package com.osmanboy.oppoproducts.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Osman Boy on 11/28/2021.
 **/
@Database(entities = [Oppo::class], version = 1, exportSchema = false)
abstract class OPPODATABASE : RoomDatabase() {

    abstract fun shopListDao(): OPPODAO

    companion object {
        private var INSTANCE: OPPODATABASE? = null
        private var LOCK = Any()
        private const val DB_NAME = "oppo_products.db"

        fun getInstance(application: Application): OPPODATABASE {

            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val db = Room.databaseBuilder(application, OPPODATABASE::class.java, DB_NAME)
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

}