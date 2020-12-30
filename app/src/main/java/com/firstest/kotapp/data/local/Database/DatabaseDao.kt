package com.firstest.kotapp.data.local.Database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import com.firstest.kotapp.data.local.models.Userlocal

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM userlocal")
    fun getAll(): List<Userlocal>

    @Query("SELECT * FROM userlocal WHERE email LIKE :email AND password LIKE :password LIMIT 1")
    fun findByName(email: String, password: String): Userlocal?

    @Insert
    fun insert(user: Userlocal)

    @Delete
    fun delete(user: Userlocal)
}