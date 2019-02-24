package com.pingpong.householdledger.PopUpMenu.PeriodSettingFragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.period_setting_page1.*

class PeriodSettingPage1 : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.period_setting_page1,container,false)
    }


}