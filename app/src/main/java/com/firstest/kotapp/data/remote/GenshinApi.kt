package com.firstest.kotapp.data.remote

import com.firstest.kotapp.data.local.models.RestGenshinCharResponse
import retrofit2.Call
import retrofit2.http.GET

interface GenshinApi{
    @get: GET("GenshinCharApi.json")

    val getGenshinCharResponse: Call<RestGenshinCharResponse>
}