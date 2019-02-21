package com.pingpong.householdledger.MainMenuFragment

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pingpong.householdledger.Adapter.CalendarDateAdapter
import com.pingpong.householdledger.Adapter.CalendarViewPagerAdapter
import com.pingpong.householdledger.CalendarPopUpActivity
import com.pingpong.householdledger.CalendarTab.CalendarViewFragment
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.MainActivity.Companion.CalendarMonthList
import com.pingpong.householdledger.MainActivity.Companion.CalendarViewFragmentList
import com.pingpong.householdledger.MainActivity.Companion.CalendarYearList
import com.pingpong.householdledger.MainActivity.Companion.MONTH
import com.pingpong.householdledger.MainActivity.Companion.Today
import com.pingpong.householdledger.MainActivity.Companion.TotalCalendarFragmentNum
import com.pingpong.householdledger.MainActivity.Companion.YEAR
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.calendar_dynamical_view.*
import kotlinx.android.synthetic.main.main_frag_calendar.*
import org.intellij.lang.annotations.JdkConstants
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : Fragment() {
    val SELECT_MONTH_INTENT = 1000
    var ChangeCalendarSynchronization = 1
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        ReStartCalendarList()

        PrevMonthBut.setOnClickListener {
            CalendarViewPagerControl("Prev")
        }
        NextMonthBut.setOnClickListener {
            CalendarViewPagerControl("Next")
        }
        CalendarYearAndMonth.setOnClickListener {
            val PopUpMenu = Intent(context, CalendarPopUpActivity::class.java)
            PopUpMenu.putExtra(YEAR, CalendarYearList[12])
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

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        fragmentManager!!.beginTransaction().replace(R.id.CalendarViewFrame,CalendarViewFragmentList[12]).commit()
        SetCalendarYearAndMonth(12)
        ChangeCalendarSynchronization = 1
        if(CalendarViewFragmentList.size!=25||CalendarYearList.size!=25||CalendarMonthList.size!=25){
            ReStartCalendarList()
        }
    }

    private fun ReStartCalendarList(){
        CalendarYearList.clear()
        CalendarMonthList.clear()
        CalendarViewFragmentList.clear()
        CreateCalendarFragmentList()
    }

    private fun CreateCalendarFragmentList() {
        for (i in 0 until TotalCalendarFragmentNum) {
            //25개월의 달력을 미리 생성한다.

            var settingYear = Today.get(Calendar.YEAR)
            var setttingMonth = Today.get(Calendar.MONTH) + i - 12

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
        fragmentManager!!.beginTransaction().replace(R.id.CalendarViewFrame,CalendarViewFragmentList[12]).commit()
        SetCalendarYearAndMonth(12)
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
                CalendarYearList.add(0,PrevYear)
                CalendarMonthList.add(0,PrevMonth)
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
                        putInt(MONTH, NextMonth)
                    }
                }
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