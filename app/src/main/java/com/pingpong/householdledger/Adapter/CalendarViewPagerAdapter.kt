package com.pingpong.householdledger.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class CalendarViewPagerAdapter(FM : FragmentManager) : FragmentPagerAdapter(FM) {

    companion object {
        var CalendarViewFragmentList : ArrayList<Fragment> = ArrayList()
    }
    override fun getItem(position: Int): Fragment {
        return CalendarViewFragmentList.get(position)
    }

    override fun getCount(): Int {
        return CalendarViewFragmentList.size
    }

}