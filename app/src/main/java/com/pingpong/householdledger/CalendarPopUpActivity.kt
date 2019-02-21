package com.pingpong.householdledger

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.TableLayout
import android.widget.TextView
import com.pingpong.householdledger.MainActivity.Companion.MONTH
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
        val MonthList = listOf<TextView>(MONTH1,MONTH2,MONTH3,MONTH4,MONTH5,MONTH6,MONTH7,MONTH8,MONTH9,MONTH10,MONTH11,MONTH12)
        PrevYearBut.setOnClickListener {
            CalendarPopUpYearTextView.text = (--Year).toString()
        }
        NextYearBut.setOnClickListener {
            CalendarPopUpYearTextView.text = (++Year).toString()
        }

        for(i in MonthList.indices){
            MonthList[i].setOnClickListener {
                val ReturnMonth = Intent()
                ReturnMonth.putExtra(MONTH,i)
                ReturnMonth.putExtra(YEAR,Year)
                setResult(Activity.RESULT_OK,ReturnMonth)
                finish()
            }
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
