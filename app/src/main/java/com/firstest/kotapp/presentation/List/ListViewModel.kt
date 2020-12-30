package com.firstest.kotapp.presentation.List

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firstest.kotapp.data.local.models.GenshinChar
import com.firstest.kotapp.data.local.models.RestGenshinCharResponse
import com.firstest.kotapp.data.remote.GenshinApi
import com.firstest.kotapp.injection.Baz.BASE_URL
import com.firstest.kotapp.presentation.Status.ApiFailure
import com.firstest.kotapp.presentation.Status.ApiStatus
import com.firstest.kotapp.presentation.Status.ApiSuccess
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class ListViewModel(
): ViewModel() {
    val apiLiveData: MutableLiveData<ApiStatus> = MutableLiveData()

    fun makeApiCall(){
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val GenshinApi: GenshinApi = retrofit.create(GenshinApi::class.java)
        val call: Call<RestGenshinCharResponse>? = GenshinApi?.getGenshinCharResponse
        if (call != null) {
            call.enqueue(object : Callback<RestGenshinCharResponse?> {
                override fun onResponse(
                        call: Call<RestGenshinCharResponse?>?,
                        response: Response<RestGenshinCharResponse?>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val genshinList = response.body()!!.results
                        apiLiveData.value = ApiSuccess(genshinList)
                    }
                }
                override fun onFailure(call: Call<RestGenshinCharResponse?>?, t: Throwable?) {
                    apiLiveData.value = ApiFailure
                }
            })
        }
    }
}