package com.pingpong.householdledger.PopUpMenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pingpong.householdledger.MainActivity
import com.pingpong.householdledger.MainActivity.Companion.DATE
import com.pingpong.householdledger.MainActivity.Companion.MonthMemoMap
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.activity_calendar_month_memo.*

class CalendarMonthMemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_month_memo)

        if(intent.hasExtra(MainActivity.DATE) && MonthMemoMap.containsKey(intent.getStringExtra(DATE))){
            MonthMemoEditText.setText(MonthMemoMap.get(intent.getStringExtra(DATE)))
        }

        EditMonthMemoButton.setOnClickListener {
            if(intent.hasExtra(MainActivity.DATE)){
                MonthMemoMap.put(intent.getStringExtra(DATE),MonthMemoEditText.text.toString())
            }
            finish()
        }
    }
}
