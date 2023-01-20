package com.mihir.sysmatask.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItems(items:List<ClassItem>)

    @Query("SELECT * FROM item_table")
    fun getItems():LiveData<List<ClassItem>>

}