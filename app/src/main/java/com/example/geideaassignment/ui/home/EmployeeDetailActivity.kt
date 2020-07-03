package com.example.geideaassignment.ui.home

import android.content.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.geideaassignment.R
import com.example.geideaassignment.data.network.responses.EmployeeListData
import com.example.geideaassignment.databinding.ActivityEmployeeDetailBinding
import com.example.geideaassignment.service.TimerService
import com.example.geideaassignment.viewmodel.EmployeeDetailViewModel

class EmployeeDetailActivity : AppCompatActivity() {
    private lateinit var mEmployeeDetailViewModel: EmployeeDetailViewModel
    private lateinit var viewBinding: ActivityEmployeeDetailBinding

    private var receiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_employee_detail)
        mEmployeeDetailViewModel = ViewModelProvider(this).get(EmployeeDetailViewModel::class.java)
        viewBinding.employeeViewmodel = mEmployeeDetailViewModel

        val empData = intent.getSerializableExtra("emp_data") as? EmployeeListData

        mEmployeeDetailViewModel.empID.value = empData?.id
        mEmployeeDetailViewModel.empName.value = empData?.employee_name
        mEmployeeDetailViewModel.empAge.value = empData?.employee_age
        mEmployeeDetailViewModel.empSalary.value = empData?.employee_salary

        startTimerService()
        getTimerReceiver()
    }

    fun startTimerService() {
        val intent = Intent(this, TimerService::class.java)
        startService(intent)
    }

    fun getTimerReceiver() {
        intentFilter = IntentFilter()
        intentFilter?.addAction("com.geidea.update_receiver")

        receiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (receiver != null) {
            unregisterReceiver(receiver)
            receiver = null
        }
    }
}
