package com.pingpong.householdledger.PopUpMenu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pingpong.householdledger.MainActivity
import com.pingpong.householdledger.MainActivity.Companion.CalDate
import com.pingpong.householdledger.MainActivity.Companion.CalMonth
import com.pingpong.householdledger.MainActivity.Companion.CalYear
import com.pingpong.householdledger.MainActivity.Companion.DATE
import com.pingpong.householdledger.MainActivity.Companion.DateInfoMap
import com.pingpong.householdledger.MainActivity.Companion.FullList
import com.pingpong.householdledger.MainActivity.Companion.MONTH
import com.pingpong.householdledger.MainActivity.Companion.Today
import com.pingpong.householdledger.MainActivity.Companion.YEAR
import com.pingpong.householdledger.R
import com.pingpong.householdledger.ReturnView.SpendListDateView
import kotlinx.android.synthetic.main.activity_specific_day_info.*

class SpecificDayInfoActivity : AppCompatActivity() {
    val SpecificDayInfoList: ArrayList<SpendListDateView> = ArrayList()
    var year = 0
    var month = 0
    var date = 0
    var dateIn8Length = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_day_info)

        LoadSpecificDayInfo(GetDateInString())

        SpecificDayAddRecordButton.setOnClickListener {
            val intent = Intent(this,AddRecordActivity::class.java)
            intent.putExtra(YEAR, year)
            intent.putExtra(MONTH, month)
            intent.putExtra(DATE,date)

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        DayInfoLayout.removeAllViews()
        LoadSpecificDayInfo(dateIn8Length)
    }

    private fun LoadSpecificDayInfo(Date : Int) {
        if(DateInfoMap.containsKey(Date)){
            for(i in FullList){
                if(i.DateInLength8==Date){
                    for(ExpenseInfo in i.ExpenseList){
                        val OneInfo = SpendListDateView(this,ExpenseInfo)
                        SpecificDayInfoList.add(OneInfo)
                        DayInfoLayout.addView(OneInfo)
                    }
                    return
                }
            }
        } else
            return
    }

    private fun GetDateInString() : Int {
        year = if (intent.hasExtra(YEAR))
            intent.getIntExtra(YEAR, CalYear(Today))
        else
            CalYear(Today)
        month = if (intent.hasExtra(MONTH))
            intent.getIntExtra(MONTH, CalMonth(Today))
        else
            CalMonth(Today)
        date = if (intent.hasExtra(DATE))
            intent.getIntExtra(DATE, CalDate(Today))
        else
            CalDate(Today)

        val monthString = if (month >= 10)
            month.toString()
        else
            "0" + month.toString()
        val dateString = if (date >= 10)
            date.toString()
        else
            "0" + date.toString()
        dateIn8Length = (year.toString() + monthString + dateString).toInt()
        return dateIn8Length
    }
}
