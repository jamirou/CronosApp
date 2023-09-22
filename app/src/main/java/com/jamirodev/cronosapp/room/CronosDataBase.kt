package com.jamirodev.cronosapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jamirodev.cronosapp.model.Cronos

@Database(entities = [Cronos::class], version = 1, exportSchema = false)
abstract class CronosDataBase: RoomDatabase() {
    abstract fun cronosDao(): CronosDataBaseDao
}