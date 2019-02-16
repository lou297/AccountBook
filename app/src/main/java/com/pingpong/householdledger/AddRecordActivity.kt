package com.pingpong.householdledger

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.pingpong.householdledger.Adapter.SpendListRecylerViewAdapter
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.DataClass.ExpenseInfo
import com.pingpong.householdledger.MainActivity.Companion.CalDate
import com.pingpong.householdledger.MainActivity.Companion.CalMonth
import com.pingpong.householdledger.MainActivity.Companion.CalYear
import com.pingpong.householdledger.MainActivity.Companion.DateInfoMap
import com.pingpong.householdledger.MainActivity.Companion.FullList
import com.pingpong.householdledger.MainActivity.Companion.MonthAndDate
import com.pingpong.householdledger.MainActivity.Companion.StatisticsAdapterList
import com.pingpong.householdledger.MainActivity.Companion.Today
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.activity_tab_menu.*
import kotlinx.android.synthetic.main.main_frag_spend_list.*
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class AddRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)
        InitialSetting()
        DateRecordField.setOnClickListener{

        }
        ConfirmBut.setOnClickListener {
            AddSpendList()
        }
        CancelBut.setOnClickListener {
            finish()
        }
    }

    private fun InitialSetting(){
        ClassificationSpinner.adapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,StatisticsAdapterList)
        val calendar = Calendar.getInstance()
        DateRecordField.text = SimpleDateFormat(MonthAndDate).format(calendar.time)
    }

    private fun AddSpendList(){
        Today = Calendar.getInstance()
        var month : String = CalMonth(Today).toString()
        var date : String =  CalDate(Today).toString()
        if(CalMonth(Today)<10)
            month = "0"+CalMonth(Today)
        if(CalDate(Today)<10)
            date =  "0"+CalDate(Today)
        val TimeInLength8 = Integer.parseInt(CalYear(Today).toString() + month + date)
//        val test : Int = (CalYear(Today).toString() + CalMonth(Today).toString() + CalDate(Today).toString()).toInt()
        //이렇게도 표현 가능하다.
        val TimeinMillis = Today.timeInMillis
        val Classify = ClassificationSpinner.selectedItem.toString()
        val Money = MoneyRecordField.text.toString().toInt()
        val PaymentMethod = PaymentMethodSpinner.selectedItem.toString()
        val Content = ContentRecordField.text.toString()
        var Expense = ExpenseInfo(TimeInLength8, TimeinMillis,Classify,Money,PaymentMethod,Content)

        if(DateInfoMap.containsKey(TimeInLength8)){
            for(i in FullList.indices){
                if(FullList[i].DateInLength8==TimeInLength8){
                    FullList[i].Spend+=Money
                    FullList[i].Total = FullList[i].Income - FullList[i].Spend
                    FullList[i].ExpenseList.add(Expense)
                    DateInfoMap.put(TimeInLength8, DateInfoMap.get(TimeInLength8)!!.plus(1))
                }
            }
        } else {
            FullList.add(DateInfo(TimeInLength8,TimeinMillis,Spend = Money, Total = -Money , ExpenseList = arrayListOf<ExpenseInfo>(Expense)))
            DateInfoMap.put(TimeInLength8,1)
        }

        SpendListRecylerViewAdapter(this,FullList).notifyDataSetChanged()
        finish()
    }
}
