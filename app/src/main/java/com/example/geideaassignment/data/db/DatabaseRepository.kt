package com.example.geideaassignment.data.db

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.geideaassignment.data.network.responses.EmployeeListData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DatabaseRepository(val appDatabase: AppDatabase?) {

    fun insertEmployee(employeeList: List<EmployeeListData>) {

        var list: ArrayList<Employee> = ArrayList()

        for (emp in employeeList) {
            list.add(Employee(emp.id, emp.employee_name, emp.employee_age, emp.employee_salary, emp.profile_image))
        }

        Observable.fromCallable {
            appDatabase?.employeeDao()?.insertEmployeeAll(list)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


    fun getAllEmp(): List<EmployeeListData>? {
        var list: ArrayList<EmployeeListData> = ArrayList()
        Observable.fromCallable {
            var dbList = appDatabase?.employeeDao()?.getEmployees()

            if (dbList != null) {
                for (emp in dbList) {
                    list.add(EmployeeListData(emp.empAge, emp.empName, emp.empSalary, emp.empID,emp.profile_image))
                }
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        return list
    }
}
