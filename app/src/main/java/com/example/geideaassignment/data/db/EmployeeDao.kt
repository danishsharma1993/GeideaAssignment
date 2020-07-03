package com.example.geideaassignment.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: Employee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployeeAll(objects: List<Employee>)

    @Query("SELECT * FROM employeeTable")
    fun getEmployees(): List<Employee>
}