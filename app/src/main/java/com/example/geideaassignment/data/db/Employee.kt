package com.example.geideaassignment.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employeeTable")
data class Employee(
    @PrimaryKey()
    @ColumnInfo(name = "empID")
    val empID: String,
    @ColumnInfo(name = "empName")
    var empName: String,
    @ColumnInfo(name = "empAge")
    var empAge: String,
    @ColumnInfo(name = "empSalary")
    var empSalary: String,
    @ColumnInfo(name = "profile_image")
    var profile_image: String
) {

}