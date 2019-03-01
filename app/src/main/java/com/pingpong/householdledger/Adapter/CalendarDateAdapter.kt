package com.pingpong.householdledger.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.MainActivity
import com.pingpong.householdledger.MainActivity.Companion.CalendarYearList
import com.pingpong.householdledger.MainActivity.Companion.DATE
import com.pingpong.householdledger.MainActivity.Companion.DateInfoMap
import com.pingpong.householdledger.MainActivity.Companion.MONTH
import com.pingpong.householdledger.MainActivity.Companion.TotalCalendarFragmentNum
import com.pingpong.householdledger.MainActivity.Companion.YEAR
import com.pingpong.householdledger.PopUpMenu.AddRecordActivity
import com.pingpong.householdledger.PopUpMenu.SpecificDayInfoActivity
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.calendar_one_day_view.view.*
import java.util.*

class CalendarDateAdapter (val context : Context, val DateInfoList : ArrayList<DateInfo>) : RecyclerView.Adapter<CalendarDateAdapter.Holder>(){
    object GetTime{
        var cal = Calendar.getInstance()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.calendar_one_day_view,parent,false))
    }

    override fun getItemCount(): Int {
        return DateInfoList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(DateInfoList.get(position) , position)
    }

    inner class Holder(view : View) : RecyclerView.ViewHolder(view){
        val Layout = view.DateLinearLayout
        val Date = view.OneDay_Date
        val Holiday = view.OneDay_Holiday
        val Income = view.OneDay_Income
        val Spend = view.OneDay_Spend
        val total = view.OneDay_Total

        fun bind(dateInfo : DateInfo, position : Int){
            var date : Int
            if(dateInfo.TimeInMilli==0L)
                date = 0
            else {
                GetTime.cal.timeInMillis = (dateInfo.TimeInMilli)
                date = GetTime.cal.get(Calendar.DATE)
            }
            Date.text = date.toString()
            if(date==0)
                Date.visibility = INVISIBLE
            else{
                Date.visibility = VISIBLE
                when(position%7){
                    6 -> Date.setTextColor(ContextCompat.getColor(context,R.color.blue))
                    0 -> Date.setTextColor(ContextCompat.getColor(context,R.color.red))
                    else -> Date.setTextColor(ContextCompat.getColor(context,R.color.black))
                }

                Layout.setOnClickListener {
                    val intent = Intent(context,SpecificDayInfoActivity::class.java)
                    intent.putExtra(YEAR, CalendarYearList[TotalCalendarFragmentNum/2])
                    intent.putExtra(MONTH, MainActivity.CalendarMonthList[TotalCalendarFragmentNum/2]-1)
                    intent.putExtra(DATE,date)

                    context.startActivity(intent)
                }
            }

            if(dateInfo.ExpenseList.size!=0){
                Income.text = dateInfo.Income.toString()
                Spend.text = dateInfo.Spend.toString()
                total.text = dateInfo.Total.toString()
            }






        }
    }

}

