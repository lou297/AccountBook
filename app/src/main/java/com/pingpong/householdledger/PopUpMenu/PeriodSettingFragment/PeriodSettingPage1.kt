package com.pingpong.householdledger.PopUpMenu.PeriodSettingFragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pingpong.householdledger.MainActivity
import com.pingpong.householdledger.MainActivity.Companion.CalDate
import com.pingpong.householdledger.MainActivity.Companion.CalMonth
import com.pingpong.householdledger.MainActivity.Companion.CalYear
import com.pingpong.householdledger.MainActivity.Companion.StatisticsEndDate
import com.pingpong.householdledger.MainActivity.Companion.StatisticsStartDate
import com.pingpong.householdledger.MainActivity.Companion.Today
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.period_setting_page1.*
import java.util.*

class PeriodSettingPage1 : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        PeriodRadioGroup.check(R.id.ThisMonthRadioButton)
        PeriodSettingConfirmButton.setOnClickListener {
            SettingPeriod(PeriodRadioGroup.checkedRadioButtonId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.period_setting_page1, container, false)
    }

    private fun SettingPeriod(checkedId: Int) {
        when (checkedId) {
            R.id.ThisMonthRadioButton -> {
                StatisticsStartDate.set(
                    MainActivity.CalYear(MainActivity.Today),
                    MainActivity.CalMonth(MainActivity.Today),
                    1
                )
                StatisticsEndDate.set(CalYear(Today), CalMonth(Today), CalDate(Today))
                activity!!.finish()
            }

            R.id.ThirtyDaysRadioButton -> {
                val CalendarFragment = PeriodSettingPage2()
                CalendarFragment.apply {
                    arguments = Bundle().apply {
                        putInt("PERIOD", 30)
                    }
                }
                fragmentManager!!.beginTransaction().replace(R.id.PeriodSettingFrameLayout, CalendarFragment).commit()

            }

            R.id.FifteenDaysRadioButton -> {
                val CalendarFragment = PeriodSettingPage2()
                CalendarFragment.apply {
                    arguments = Bundle().apply {
                        putInt("PERIOD", 15)
                    }
                }
                fragmentManager!!.beginTransaction().replace(R.id.PeriodSettingFrameLayout, CalendarFragment).commit()

            }

            R.id.OneWeekRadioButton -> {
                val CalendarFragment = PeriodSettingPage2()
                CalendarFragment.apply {
                    arguments = Bundle().apply {
                        putInt("PERIOD", 7)
                    }
                }
                fragmentManager!!.beginTransaction().replace(R.id.PeriodSettingFrameLayout, CalendarFragment).commit()
            }
        }


    }
}