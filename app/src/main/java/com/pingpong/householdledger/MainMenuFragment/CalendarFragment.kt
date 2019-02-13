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
import com.pingpong.householdledger.Adapter.CalendarViewPagerAdapter.Companion.CalendarViewFragmentList
import com.pingpong.householdledger.CalendarPopUpActivity
import com.pingpong.householdledger.CalendarTab.CalendarViewFragment
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.MainActivity.Companion.MONTH
import com.pingpong.householdledger.MainActivity.Companion.Today
import com.pingpong.householdledger.MainActivity.Companion.YEAR
import com.pingpong.householdledger.MainMenuFragment.CalendarFragment.Date.CalendarMonthList
import com.pingpong.householdledger.MainMenuFragment.CalendarFragment.Date.CalendarYearList
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.calendar_dynamical_view.*
import kotlinx.android.synthetic.main.main_frag_calendar.*
import org.intellij.lang.annotations.JdkConstants
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : Fragment() {
    val SELECT_MONTH_INTENT = 1000
    object Date{
        var CalendarYearList = mutableListOf<Int>();
        var CalendarMonthList = mutableListOf<Int>();
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        CreateCalendarFragmentList()
        CalendarViewPager.adapter = CalendarViewPagerAdapter(childFragmentManager)
        CalendarViewPager.currentItem = 12
        SetCalendarYearAndMonth(CalendarViewPager.currentItem)
        PrevMonthBut.setOnClickListener {
            CalendarViewPagerControl("Prev")
        }
        NextMonthBut.setOnClickListener {
            CalendarViewPagerControl("Next")
        }
        CalendarYearAndMonth.setOnClickListener {
            val PopUpMenu = Intent(context,CalendarPopUpActivity::class.java)
            PopUpMenu.putExtra(YEAR, CalendarYearList[CalendarViewPager.currentItem])
            startActivityForResult(PopUpMenu,SELECT_MONTH_INTENT)
        }
        super.onActivityCreated(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_frag_calendar,container,false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==SELECT_MONTH_INTENT){
            if(resultCode==RESULT_OK){

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun CreateCalendarFragmentList(){
        for(i in 0 .. 24){
            //25개월의 달력을 미리 생성한다.

            var settingYear = Today.get(Calendar.YEAR)
            var setttingMonth = Today.get(Calendar.MONTH) + i - 12

            while(setttingMonth<0 || setttingMonth>11){
                if(setttingMonth<0) {
                    setttingMonth += 12
                    settingYear--
                }
                if(setttingMonth>11) {
                    setttingMonth -= 12
                    settingYear++
                }
            }
            CalendarYearList.add(settingYear)
            CalendarMonthList.add(setttingMonth+1)
            val Frags = CalendarViewFragment()
            Frags.apply {
                arguments = Bundle().apply {
                    putInt(YEAR,settingYear)
                    putInt(MONTH,setttingMonth)
                }
            }
            CalendarViewFragmentList.add(Frags)
        }
    }

    private fun CalendarViewPagerControl(s: String) {
        when(s){
            "Prev" -> {CalendarViewPager.currentItem--
                        SetCalendarYearAndMonth(CalendarViewPager.currentItem)
            }
            "Next" -> {CalendarViewPager.currentItem++
                        SetCalendarYearAndMonth(CalendarViewPager.currentItem)

            }
        }
    }

    private fun SetCalendarYearAndMonth(index : Int){
        CalendarYearAndMonth.text = String.format("${CalendarYearList[index]}년 ${CalendarMonthList[index]}월")
    }


}