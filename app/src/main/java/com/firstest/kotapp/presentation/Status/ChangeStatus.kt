package com.firstest.kotapp.presentation.main

import com.firstest.kotapp.domain.entity.User

sealed class ChangeStatus

data class ChangeSuccess(val user : User) : ChangeStatus()

object NoEmail : ChangeStatus()
object InvalidM : ChangeStatus()
object Nopassword : ChangeStatus()
object ExistingAcc : ChangeStatus()
object UKN : ChangeStatus()

