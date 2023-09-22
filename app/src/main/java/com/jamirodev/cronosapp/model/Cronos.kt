package com.jamirodev.cronosapp.model

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//ENTITIES = Table, ATTRIBUTE = Field
@Entity(tableName = "cronos")
data class Cronos(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "crono")
    val crono: Long
)

