package com.pingpong.householdledger.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.pingpong.householdledger.MainActivity.Companion.CalendarViewFragmentList

class CalendarViewPagerAdapter(FM : FragmentManager) : FragmentPagerAdapter(FM) {

    override fun getItem(position: Int): Fragment {
        return CalendarViewFragmentList.get(position)
    }

    override fun getCount(): Int {
        return CalendarViewFragmentList.size
    }

}