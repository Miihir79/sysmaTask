package com.mihir.sysmatask.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "item_table")
data class ClassItem(
    @PrimaryKey
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)