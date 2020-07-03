package com.example.geideaassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.geideaassignment.data.db.AppDatabase
import com.example.geideaassignment.data.db.DatabaseRepository
import com.example.geideaassignment.data.network.MyApi
import com.example.geideaassignment.data.network.responses.BaseResponse
import com.example.geideaassignment.data.network.responses.EmployeeListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeListViewModel(application: Application) : AndroidViewModel(application) {
    var employees: MutableList<EmployeeListData> = mutableListOf()
    var mEmployeeListLiveData: MutableLiveData<String> = MutableLiveData()
    var mDatabaseRepository: DatabaseRepository? = null
    private val context = getApplication<Application>().applicationContext

    var appDatabase: AppDatabase?

    init {
        appDatabase = AppDatabase.getAppDataBase()?.let { it }
    }

    fun insert(empList: List<EmployeeListData>) {
        mDatabaseRepository = DatabaseRepository(appDatabase)
        mDatabaseRepository?.insertEmployee(empList)
    }

    fun getEmployeeOnline() {

        MyApi().employeeList()
            .enqueue(object : Callback<BaseResponse<List<EmployeeListData>>> {
                override fun onFailure(
                    call: Call<BaseResponse<List<EmployeeListData>>>,
                    t: Throwable
                ) {

                }

                override fun onResponse(
                    call: Call<BaseResponse<List<EmployeeListData>>>,
                    response: Response<BaseResponse<List<EmployeeListData>>>
                ) {
                    if (response.isSuccessful) {
                        employees = response.body()?.data as ArrayList
                        println("----status--" + response.body()?.data)
                        insert(employees)

                        mEmployeeListLiveData.value = ""
                    } else {

                    }
                }
            })
    }

    fun getEmployeeOffline() :List<EmployeeListData>{
        mDatabaseRepository = DatabaseRepository(appDatabase)
        employees =
            mDatabaseRepository?.getAllEmp() as MutableList<EmployeeListData>

        return employees
    }
}