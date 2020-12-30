package com.firstest.kotapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firstest.kotapp.domain.UseCase.CreateUserUseCase
import com.firstest.kotapp.domain.UseCase.GetUserUseCase
import com.firstest.kotapp.domain.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
      private val createUserUseCase: CreateUserUseCase,
      private val getUserUseCase: GetUserUseCase
) : ViewModel(){

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser, password)
            val loginStatus = if (user != null ) {
                LoginSuccess(
                        user.email,
                        user.password
                )
                //LoginSuccess(user.password)
            } else {
                LoginError
            }
            withContext(Dispatchers.Main) {
                loginLiveData.value = loginStatus
            }

        }
    }


    }
