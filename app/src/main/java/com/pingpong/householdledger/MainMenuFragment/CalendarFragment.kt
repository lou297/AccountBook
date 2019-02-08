package com.pingpong.householdledger.MainMenuFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pingpong.householdledger.Adapter.CalendarDateAdapter
import com.pingpong.householdledger.Adapter.CalendarViewPagerAdapter
import com.pingpong.householdledger.Adapter.CalendarViewPagerAdapter.Companion.CalendarViewFragmentList
import com.pingpong.householdledger.CalendarTab.CalendarViewFragment
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.MainActivity.Companion.MONTH
import com.pingpong.householdledger.MainActivity.Companion.YEAR
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.calendar_dynamical_view.*
import kotlinx.android.synthetic.main.main_frag_calendar.*
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        CreateCalendarFragmentList()
        CalendarViewPager.adapter = CalendarViewPagerAdapter(childFragmentManager)
        CalendarViewPager.currentItem = 12
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_frag_calendar,container,false)
    }

    private fun CreateCalendarFragmentList(){
        for(i in 0 .. 24){
            //25개월의 달력을 미리 생성한다.
            val calendar = Calendar.getInstance()
            val ThisYear = calendar.get(java.util.Calendar.YEAR)
            val ThisMonth = calendar.get(java.util.Calendar.MONTH)

            var settingYear = ThisYear
            var setttingMonth = ThisMonth + i - 12

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
            val Frags = CalendarViewFragment()
            Frags.apply {
                arguments = Bundle().apply {
                    putInt(YEAR,settingYear)
                    putInt(MONTH,setttingMonth)
                }
            }
            CalendarViewFragmentList.add(Frags)
            Log.d("test",i.toString())
        }
    }


}