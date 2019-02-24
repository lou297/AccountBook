package com.pingpong.householdledger.PopUpMenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.pingpong.householdledger.PopUpMenu.PeriodSettingFragment.PeriodSettingPage1
import com.pingpong.householdledger.PopUpMenu.PeriodSettingFragment.PeriodSettingPage2
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.activity_specific_period_setting.*
import kotlinx.android.synthetic.main.period_setting_page1.*

class SpecificPeriodSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_period_setting)


    }

    override fun onStart() {
        supportFragmentManager.beginTransaction().replace(R.id.PeriodSettingFrameLayout, PeriodSettingPage1()).commit()
        super.onStart()
    }
}
