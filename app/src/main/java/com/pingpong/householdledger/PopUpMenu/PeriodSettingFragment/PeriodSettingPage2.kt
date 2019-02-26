package com.pingpong.householdledger.PopUpMenu.PeriodSettingFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pingpong.householdledger.MainActivity
import com.pingpong.householdledger.MainActivity.Companion.StatisticsEndDate
import com.pingpong.householdledger.MainActivity.Companion.StatisticsStartDate
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.main_frag_statistics.*
import kotlinx.android.synthetic.main.period_setting_page2.*
import java.text.SimpleDateFormat
import java.util.*

class PeriodSettingPage2 : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var DateSelected = 0
        var period : Int = 0
        val StartCalendar = Calendar.getInstance()
        val EndCalendar = Calendar.getInstance()
        if(arguments!!.isEmpty)
            fragmentManager!!.beginTransaction().replace(R.id.PeriodSettingFrameLayout, PeriodSettingPage1()).commit()
        else
            period = arguments!!.getInt("PERIOD")

        PeriodSettingCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            DateSelected = 1
            StartCalendar.set(year,month,dayOfMonth)
            EndCalendar.set(year,month,dayOfMonth)
            EndCalendar.add(Calendar.DAY_OF_MONTH,period)
            val DF = SimpleDateFormat(MainActivity.MonthAndDate)
            CalendarPeriodTextView.text = DF.format(StartCalendar.time) + " ~ " + DF.format(EndCalendar.time)
        }

        PeriodCalendarConfirmButton.setOnClickListener {
            if(DateSelected==1){
                StatisticsStartDate = StartCalendar
                StatisticsEndDate = EndCalendar
                activity!!.finish()
            }
            else
                Toast.makeText(context,"시작 일을 선택해주세요.",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.period_setting_page2,container,false)
    }
}