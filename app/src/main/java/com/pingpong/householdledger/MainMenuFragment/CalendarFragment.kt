package com.pingpong.householdledger.MainMenuFragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pingpong.householdledger.PopUpMenu.CalendarPopUpActivity
import com.pingpong.householdledger.CalendarTab.CalendarViewFragment
import com.pingpong.householdledger.MainActivity.Companion.CalendarMonthList
import com.pingpong.householdledger.MainActivity.Companion.CalendarViewFragmentList
import com.pingpong.householdledger.MainActivity.Companion.CalendarYearList
import com.pingpong.householdledger.MainActivity.Companion.MONTH
import com.pingpong.householdledger.MainActivity.Companion.Today
import com.pingpong.householdledger.MainActivity.Companion.TotalCalendarFragmentNum
import com.pingpong.householdledger.MainActivity.Companion.YEAR
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.main_frag_calendar.*
import java.util.*

class CalendarFragment : Fragment() {
    val SELECT_MONTH_INTENT = 1000
    var ChangeCalendarSynchronization = 1
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        ReStartCalendarList(Today.get(Calendar.YEAR),Today.get(Calendar.MONTH))

        PrevMonthBut.setOnClickListener {
            CalendarViewPagerControl("Prev")
        }
        NextMonthBut.setOnClickListener {
            CalendarViewPagerControl("Next")
        }
        CalendarYearAndMonth.setOnClickListener {
            val PopUpMenu = Intent(context, CalendarPopUpActivity::class.java)
            PopUpMenu.putExtra(YEAR, CalendarYearList[TotalCalendarFragmentNum/2])
            startActivityForResult(PopUpMenu, SELECT_MONTH_INTENT)
        }
        super.onActivityCreated(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_frag_calendar, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SELECT_MONTH_INTENT) {
            if (resultCode == RESULT_OK) {
                if(data!=null&&data.hasExtra(YEAR) && data.hasExtra(MONTH)){
                    ReStartCalendarList(data.getIntExtra(YEAR,Today.get(Calendar.YEAR)),data.getIntExtra(MONTH,Today.get(Calendar.MONTH)))
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        Log.d("test","resume")
        fragmentManager!!.beginTransaction().replace(R.id.CalendarViewFrame,CalendarViewFragmentList[TotalCalendarFragmentNum/2]).commit()
        SetCalendarYearAndMonth(TotalCalendarFragmentNum/2)
        ChangeCalendarSynchronization = 1
        if(CalendarViewFragmentList.size!=TotalCalendarFragmentNum||CalendarYearList.size!=TotalCalendarFragmentNum||CalendarMonthList.size!=TotalCalendarFragmentNum){
            ReStartCalendarList(Today.get(Calendar.YEAR),Today.get(Calendar.MONTH))
        }
    }

    private fun ReStartCalendarList(year: Int , month : Int){
        CalendarYearList.clear()
        CalendarMonthList.clear()
        CalendarViewFragmentList.clear()
        CreateCalendarFragmentList(year,month)
    }

    private fun CreateCalendarFragmentList(year: Int , month : Int) {
        for (i in 0 until TotalCalendarFragmentNum) {
            //25개월의 달력을 미리 생성한다.

            var settingYear = year
            var setttingMonth = month + i - 12

            while (setttingMonth < 0 || setttingMonth > 11) {
                if (setttingMonth < 0) {
                    setttingMonth += 12
                    settingYear--
                }
                if (setttingMonth > 11) {
                    setttingMonth -= 12
                    settingYear++
                }
            }
            CalendarYearList.add(settingYear)
            CalendarMonthList.add(setttingMonth + 1)
            val Frags = CalendarViewFragment()
            Frags.apply {
                arguments = Bundle().apply {
                    putInt(YEAR, settingYear)
                    putInt(MONTH, setttingMonth)
                }
            }
            CalendarViewFragmentList.add(Frags)
        }
        fragmentManager!!.beginTransaction().replace(R.id.CalendarViewFrame,CalendarViewFragmentList[TotalCalendarFragmentNum/2]).commit()
        SetCalendarYearAndMonth(TotalCalendarFragmentNum/2)
    }

    private fun CalendarViewPagerControl(s: String) {
        if(ChangeCalendarSynchronization==1){
            ChangeCalendarSynchronization=0
            when (s) {
                "Prev" -> {
                    fragmentManager!!.beginTransaction().replace(R.id.CalendarViewFrame,CalendarViewFragmentList[11]).commit()
                    SetCalendarYearAndMonth(11)
                    AddCalendarFragment(s)
                }
                "Next" -> {
                    fragmentManager!!.beginTransaction().replace(R.id.CalendarViewFrame,CalendarViewFragmentList[13]).commit()
                    SetCalendarYearAndMonth(13)
                    AddCalendarFragment(s)
                }
            }
        }
    }

    private fun SetCalendarYearAndMonth(index: Int) {
        CalendarYearAndMonth.text = String.format("${CalendarYearList[index]}년 ${CalendarMonthList[index]}월")
    }

    private fun AddCalendarFragment(s: String) {
        when (s) {
            "Prev" -> {
                var PrevMonth = (CalendarMonthList.get(0) )-1
                var PrevYear = CalendarYearList.get(0)
                if(PrevMonth==0){
                    PrevMonth=12
                    PrevYear--
                }

                val Frags = CalendarViewFragment()
                Frags.apply {
                    arguments = Bundle().apply {
                        putInt(YEAR, PrevYear)
                        putInt(MONTH, PrevMonth)
                    }
                }
                Log.d("Test","전 $PrevYear 년 $PrevMonth 월")
                CalendarYearList.add(0,PrevYear)
                CalendarMonthList.add(0,PrevMonth-1)
                CalendarViewFragmentList.add(0,Frags)
                CalendarViewFragmentList.removeAt(CalendarViewFragmentList.size-1)
                CalendarMonthList.removeAt(CalendarMonthList.size-1)
                CalendarYearList.removeAt(CalendarYearList.size-1)
            }
            "Next" -> {
                var NextMonth = (CalendarMonthList.get(CalendarMonthList.size-1) )+1
                var NextYear = CalendarYearList.get(CalendarYearList.size-1)
                if(NextMonth==13){
                    NextMonth=1
                    NextYear++
                }

                val Frags = CalendarViewFragment()
                Frags.apply {
                    arguments = Bundle().apply {
                        putInt(YEAR, NextYear)
                        putInt(MONTH, NextMonth-1)
                    }
                }
                Log.d("Test","다음 $NextYear 년 $NextMonth 월")
                CalendarYearList.add(NextYear)
                CalendarMonthList.add(NextMonth)
                CalendarViewFragmentList.add(Frags)
                CalendarViewFragmentList.removeAt(0)
                CalendarMonthList.removeAt(0)
                CalendarYearList.removeAt(0)
            }
        }
        ChangeCalendarSynchronization =1
        Log.d("test", CalendarYearList.toString())
        Log.d("test", CalendarMonthList.toString())
    }


}