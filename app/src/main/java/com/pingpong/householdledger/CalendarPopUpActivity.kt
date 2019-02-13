package com.pingpong.householdledger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.pingpong.householdledger.MainActivity.Companion.Today
import com.pingpong.householdledger.MainActivity.Companion.YEAR
import kotlinx.android.synthetic.main.activity_calendar_pop_up.*
import java.util.*

class CalendarPopUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_calendar_pop_up)
        var Year = GetYEAR()

        PrevYearBut.setOnClickListener {
            CalendarPopUpYearTextView.text = (--Year).toString()
        }
        NextYearBut.setOnClickListener {
            CalendarPopUpYearTextView.text = (++Year).toString()
        }
    }

    private fun GetYEAR() : Int{
        var year = Today.get(Calendar.YEAR)
        if(intent.hasExtra(YEAR)){
            year = intent.getIntExtra(YEAR,year)
        }
        CalendarPopUpYearTextView.text = year.toString()
        return year
    }
}
