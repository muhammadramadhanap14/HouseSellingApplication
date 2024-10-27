package com.example.housesellingapputama

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://ninetyninedotco-b7299.asia-southeast1.firebasedatabase.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val apiService = retrofit.create(ApiService::class.java)