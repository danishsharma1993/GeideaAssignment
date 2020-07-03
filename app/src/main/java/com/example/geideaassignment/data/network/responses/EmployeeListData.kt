package com.example.geideaassignment.data.network.responses

import java.io.Serializable

data class EmployeeListData(
    var employee_age: String,
    var employee_name: String,
    var employee_salary: String,
    var id: String,
    var profile_image: String
) : Serializable