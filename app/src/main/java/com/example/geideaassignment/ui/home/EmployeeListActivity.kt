package com.example.geideaassignment.ui.home

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.geideaassignment.R
import com.example.geideaassignment.adapter.EmployeeListAdapter
import com.example.geideaassignment.databinding.ActivityEmployeeListBinding
import com.example.geideaassignment.viewmodel.EmployeeListViewModel

class EmployeeListActivity : AppCompatActivity() {
    private lateinit var mEmployeeListViewModel: EmployeeListViewModel
    private lateinit var viewBinding: ActivityEmployeeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_employee_list)
        mEmployeeListViewModel =
            ViewModelProvider(this).get(
                EmployeeListViewModel::class.java
            )
        viewBinding.employeeViewmodel = mEmployeeListViewModel

        setAdapter()
    }

    private fun setAdapter() {
        mEmployeeListViewModel.mEmployeeListLiveData.observe(this, Observer {

            val employeeListAdapter =
                EmployeeListAdapter(mEmployeeListViewModel.employees, this)
            viewBinding.employeeRecyclerView.adapter = employeeListAdapter
        })

        if (!isNetworkConnected()) {

            val employeesFromDB = mEmployeeListViewModel.getEmployeeOffline()
            val employeeListAdapter = EmployeeListAdapter(employeesFromDB!!, this)
            viewBinding.employeeRecyclerView.adapter = employeeListAdapter
        } else {
            mEmployeeListViewModel.getEmployeeOnline()
        }
    }

    @Suppress("DEPRECATION")
    private fun isNetworkConnected(): Boolean {
        val cm =
            getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }
}
