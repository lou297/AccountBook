package com.pingpong.householdledger

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pingpong.householdledger.MainActivity.Companion.DATE
import com.pingpong.householdledger.MainActivity.Companion.MONTH
import com.pingpong.householdledger.MainActivity.Companion.Today
import com.pingpong.householdledger.MainActivity.Companion.YEAR
import kotlinx.android.synthetic.main.activity_date_picker.*
import java.util.*

class DatePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)
        var year = MainActivity.Today.get(Calendar.YEAR)
        var month = Today.get(Calendar.MONTH)
        var date = Today.get(Calendar.DATE)
        if(intent.hasExtra(MainActivity.YEAR)){
            year = intent.getIntExtra(MainActivity.YEAR,year)
        }
        if(intent.hasExtra(MainActivity.MONTH)){
            month = intent.getIntExtra(MainActivity.MONTH,month)
        }
        if(intent.hasExtra(MainActivity.DATE)){
            date = intent.getIntExtra(MainActivity.DATE,date)
        }

        val Cal = Calendar.getInstance()
        Cal.set(year,month,date)
        DatePicker.date = Cal.timeInMillis

        DatePicker.setOnDateChangeListener { view, Year, Month, dayOfMonth ->
            val ReturnPicker = Intent()
            ReturnPicker.putExtra(YEAR,Year)
            ReturnPicker.putExtra(MONTH,Month)
            ReturnPicker.putExtra(DATE,dayOfMonth)
            setResult(Activity.RESULT_OK,ReturnPicker)
            finish()
        }

    }
}
