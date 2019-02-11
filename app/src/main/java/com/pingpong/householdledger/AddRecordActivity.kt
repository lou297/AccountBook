package com.pingpong.householdledger

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.pingpong.householdledger.DataClass.ExpenseInfo
import com.pingpong.householdledger.MainActivity.Companion.FullList
import com.pingpong.householdledger.MainActivity.Companion.MonthAndDate
import com.pingpong.householdledger.MainActivity.Companion.StatisticsAdapterList
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.activity_tab_menu.*
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
        var Expense = ExpenseInfo(2019,1,1,ClassificationSpinner.selectedItem.toString(),Integer.parseInt(MoneyRecordField.text.toString()),PaymentMethodSpinner.selectedItem.toString(),null)
        FullList.add(Expense)
    }
}
