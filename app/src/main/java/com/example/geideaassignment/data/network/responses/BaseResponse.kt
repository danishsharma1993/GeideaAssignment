package com.example.geideaassignment.data.network.responses

data class BaseResponse<T>(
    var status: String,
    var data: T
)