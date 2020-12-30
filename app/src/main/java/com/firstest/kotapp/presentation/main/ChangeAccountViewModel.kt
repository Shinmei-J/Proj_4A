package com.firstest.kotapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firstest.kotapp.domain.UseCase.CreateUserUseCase
import com.firstest.kotapp.domain.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChangeAccountViewModel(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {
    val changeLiveData: MutableLiveData<ChangeStatus> = MutableLiveData()

   fun onClickedChange (emailUser: String, userPassword: String) {

        val user = User(emailUser, userPassword)
        viewModelScope.launch(Dispatchers.IO) {
            val change = createUserUseCase.invoke(emailUser, userPassword)
            val changeStatus = when {
                change.equals("NoEmail") -> NoEmail
                change.equals("InvalidEmail") -> InvalidM
                change.equals("NoPassword") -> Nopassword
                change.equals("Existing account") -> ExistingAcc
                change.equals("Success") -> ChangeSuccess(user)
                else -> UKN
            }

            withContext(Dispatchers.Main) {
                changeLiveData.value = changeStatus
            }
        }
    }
}

