package com.mihir.sysmatask.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ClassItem::class], version = 1, exportSchema = false)

abstract class Database : RoomDatabase() {

    abstract fun Dao():DaoInterface

    companion object{
        private var INSTANCE: com.mihir.sysmatask.model.Database?=null

        fun getDatabase(context: Context):com.mihir.sysmatask.model.Database{
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.mihir.sysmatask.model.Database::class.java,
                    "item_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}