package com.pingpong.householdledger.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.pingpong.householdledger.MainMenuFragment.CalendarFragment
import com.pingpong.householdledger.MainMenuFragment.SettingFragment
import com.pingpong.householdledger.MainMenuFragment.SpendListFragment
import com.pingpong.householdledger.MainMenuFragment.StatisticsFragment

class MainPagerAdapter(FM: FragmentManager) : FragmentPagerAdapter(FM) {
    override fun getItem(position: Int): Fragment? {
        return when(position){
            0 -> CalendarFragment()

            1 -> SpendListFragment()

            2 -> StatisticsFragment()

            3 -> SettingFragment()

            else -> null
        }
    }

    override fun getCount(): Int {
        return 4
    }

}