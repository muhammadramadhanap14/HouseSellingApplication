package com.example.housesellingapputama

import ListingDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("listings.json")
    fun getSearchResults(): Call<List<Listing>>

    @GET("details/{id}.json")
    fun getListingDetail(@Path("id") id: Int): Call<ListingDetail>
}