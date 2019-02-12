package com.pingpong.householdledger.CalendarTab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pingpong.householdledger.Adapter.CalendarDateAdapter
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.MainActivity.Companion.MONTH
import com.pingpong.householdledger.MainActivity.Companion.YEAR
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.calendar_dynamical_view.*
import kotlinx.android.synthetic.main.main_frag_calendar.*
import java.util.*

class CalendarViewFragment :Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val YEAR = arguments!!.getInt(YEAR)
        val MONTH = arguments!!.getInt(MONTH)
        CreateMonthViewDynamically(YEAR,MONTH)


        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.calendar_dynamical_view,container,false)
    }

    private fun CreateMonthViewDynamically(YEAR : Int, MONTH : Int){
        CalendarYearAndMonth.text = (MONTH+1).toString() + "월"
        var calendar = Calendar.getInstance()
        calendar.set(YEAR, MONTH, 1)

        val DateInfoList = ArrayList<DateInfo>()

        for (i in 1..calendar.get(Calendar.DAY_OF_WEEK) - 1) {
            DateInfoList.add(DateInfo())
        }

        for (i in 1..calendar.getActualMaximum(java.util.Calendar.DATE)) {
            calendar.set(YEAR, MONTH, i)
//            Log.d("test",calendar.get(Calendar.DATE).toString())
            DateInfoList.add(DateInfo(TimeInMilli= calendar.timeInMillis))
        }

        CalendarMonthRecyclerView.layoutManager = GridLayoutManager(context, 7)
        CalendarMonthRecyclerView.adapter = CalendarDateAdapter(this.context!!,DateInfoList)
    }
}