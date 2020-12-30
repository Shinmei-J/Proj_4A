package com.firstest.kotapp.domain.UseCase

import com.firstest.kotapp.data.Repository.UserRepository
import com.firstest.kotapp.domain.entity.User

class GetUserUseCase(
        private val userRepository: UserRepository
        ) {
    suspend fun invoke(email: String, password: String) : User? {
       return userRepository.getUser(email, password)
    }
}
