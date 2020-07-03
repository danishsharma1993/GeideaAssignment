package com.example.geideaassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geideaassignment.data.db.Employee
import com.example.geideaassignment.data.network.responses.EmployeeListData
import com.example.geideaassignment.databinding.EmployeeListItemBinding
import com.example.geideaassignment.ui.home.EmployeeDetailActivity

class EmployeeListAdapter(
    val employeeList: List<EmployeeListData>,
    context: Context
) : RecyclerView.Adapter<BaseViewHolder>() {

    var mContext: Context? = null

    init {
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val mEmployeeRowItemBinding = EmployeeListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return EmployeeItemViewHolder(mEmployeeRowItemBinding)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        return holder.onBind(position)
    }

    inner class EmployeeItemViewHolder(private val employeeItemBinding: EmployeeListItemBinding) :
        BaseViewHolder(itemView = employeeItemBinding.root) {

        override fun onBind(position: Int) {
            val employeeItem = employeeList[position]
            employeeItemBinding.employeeItem = employeeItem
            employeeItemBinding.executePendingBindings()
            itemView.setOnClickListener {
                val intent = Intent(mContext, EmployeeDetailActivity::class.java)
                intent.putExtra("emp_data", employeeItem)
                mContext?.startActivity(intent)
            }
        }
    }
}
