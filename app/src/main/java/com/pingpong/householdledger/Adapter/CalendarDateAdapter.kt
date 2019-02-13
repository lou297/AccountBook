package com.pingpong.householdledger.Adapter

import android.annotation.SuppressLint
import android.content.Context
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
        val Date = view.OneDay_Date
        val Holiday = view.OneDay_Holiday
        val Earn = view.OneDay_Earn
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
            }



        }
    }

}

