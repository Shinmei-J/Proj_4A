package com.firstest.kotapp.domain.UseCase

import com.firstest.kotapp.data.Repository.UserRepository
import com.firstest.kotapp.domain.entity.User

class CreateUserUseCase(
        private val userRepository: UserRepository
        ) {
    suspend fun invoke(emailUser: String, userPassword: String): String{
       return userRepository.createUser(emailUser, userPassword)

    }
}