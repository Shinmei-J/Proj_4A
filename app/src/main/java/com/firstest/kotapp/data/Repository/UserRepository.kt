package com.firstest.kotapp.data.Repository

import com.firstest.kotapp.data.local.Database.DatabaseDao
import com.firstest.kotapp.data.local.models.toData
import com.firstest.kotapp.data.local.models.toEntity
import com.firstest.kotapp.domain.entity.User

class UserRepository(
    private val databaseDao: DatabaseDao
) {

    suspend fun createUser(emailUser: String, userPassword: String): String{
        when{
            emailUser == "" -> return "NoEmail"
            !emailUser.contains("@") || !emailUser.contains(".") -> return "InvalidEmail"
            userPassword == "" -> return "NoPassword"
            getUser(emailUser,userPassword) != null -> return "AccountAlreadyExisting"
        }
        val user = User(emailUser, userPassword)
        databaseDao.insert(user.toData())
        return "Success"
    }

    fun getUser(email: String, password: String) : User?{
        val userlocal = databaseDao.findByName(email, password)
        return userlocal?.toEntity()

    }
}