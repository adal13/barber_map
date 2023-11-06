package com.example.barbershop.ui.Api.client

import com.example.barbershop.ui.Api.services.ApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
//    val URL_BASE = "http://localhost:8093/"
//    val URL_BASE = "http://192.168.143.41:8093/"
//    private var retrofit: Retrofit? = null
//    private lateinit var apiService: ApiServices
//
//    open fun getApiService(): ApiServices? {
//        if (!::apiService.isInitialized) {
//            if (retrofit == null) {
//                retrofit = Retrofit.Builder()
//                    .baseUrl(URL_BASE)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//            }
//            return retrofit!!.create(ApiServices::class.java)
//        }
//        return apiService;
//    }

    val URL_BASE = "http://10.0.2.2:8093/"
//    val URL_BASE = "https://8869-148-244-93-42.ngrok-free.app/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val consumirApi = retrofit.create(ApiServices::class.java)


}