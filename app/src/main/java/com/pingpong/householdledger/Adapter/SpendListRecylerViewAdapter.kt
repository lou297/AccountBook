package com.pingpong.householdledger.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pingpong.householdledger.DataClass.ExpenseInfo
import com.pingpong.householdledger.MainActivity.Companion.CalMonth
import com.pingpong.householdledger.MainActivity.Companion.CalYear
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.list_one_day_view.view.*
import java.util.*

class SpendListRecylerViewAdapter (val context : Context, val expenseList : ArrayList<ExpenseInfo>): RecyclerView.Adapter<SpendListRecylerViewAdapter.Holder>() {
    object time{
        var CalForHolder1 = Calendar.getInstance()
        var CalForHolder2 = Calendar.getInstance()
    }
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.list_one_day_view,parent,false))
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        return holder.bind(expenseList.get(position) ,position)
    }


    inner class Holder(view : View) : RecyclerView.ViewHolder(view){
        val Date = view.ListDate
        val TotalEarn = view.OneDayTotalEarn
        val TotalSpend =view.OneDayTotalSpend
        val TotalMoney = view.OneDayTotalMoney
        fun bind( Info : ExpenseInfo, position : Int){

        }

    }
}