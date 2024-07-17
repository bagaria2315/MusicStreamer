package com.example.scaleraudiostreaming

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("x-rapidapi-key:280e9ac571msh5d12d65992b77f1p159406jsn86cba7302cf0", "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query:String) : Call<MyData>
}