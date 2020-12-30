package com.firstest.kotapp.presentation.Status

import com.firstest.kotapp.data.local.models.GenshinChar

sealed class ApiStatus

data class ApiSuccess(val genshinList : List<GenshinChar>) : ApiStatus()

object ApiFailure : ApiStatus()