package com.firstest.kotapp.data.local.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.firstest.kotapp.data.local.models.Userlocal

@Database(entities = arrayOf(
    Userlocal::class),
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}
