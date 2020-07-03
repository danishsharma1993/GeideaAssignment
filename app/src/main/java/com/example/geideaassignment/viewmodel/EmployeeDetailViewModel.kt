package com.example.geideaassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class EmployeeDetailViewModel(application: Application) : AndroidViewModel(application) {
    var empID: MutableLiveData<String> = MutableLiveData()
    var empName: MutableLiveData<String> = MutableLiveData()
    var empAge: MutableLiveData<String> = MutableLiveData()
    var empSalary: MutableLiveData<String> = MutableLiveData()

}