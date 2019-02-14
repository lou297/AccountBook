package com.pingpong.householdledger.ReturnView

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.list_one_day_view.view.*

class SpendListDateView(context : Context, info : DateInfo) : LinearLayout(context) {
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.list_one_day_view,this,true)
        ListDate.text = info.DateInLength8.toString()
        OneDayTotalEarn.text = info.Income.toString()
        OneDayTotalSpend.text = info.Spend.toString()
        OneDayTotalMoney.text= info.Total.toString()
    }
}