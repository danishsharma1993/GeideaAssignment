package com.example.geideaassignment.data.network

import com.example.geideaassignment.data.network.responses.BaseResponse
import com.example.geideaassignment.data.network.responses.EmployeeListData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {
    @GET("v1/employees")
    fun employeeList(): Call<BaseResponse<List<EmployeeListData>>>

    companion object {
        operator fun invoke(
        ): MyApi {

            val okkHttpclient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("http://dummy.restapiexample.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}