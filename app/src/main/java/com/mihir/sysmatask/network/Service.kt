package com.mihir.sysmatask.network

import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("/posts")
    suspend fun getItemsFromNetwork():Response<com.mihir.sysmatask.model.Class>
}