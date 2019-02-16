package com.pingpong.householdledger.ReturnView

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.DataClass.ExpenseInfo
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.list_one_day_view.view.*
import kotlinx.android.synthetic.main.list_one_info_view.view.*

class SpendListDateView(context : Context, info : ExpenseInfo) : LinearLayout(context) {
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.list_one_info_view,this,true)
        InfoTime.text = info.DateInLength8.toString()
        InfoClassification.text = info.Classification
        InfoAmount.text = info.Amount.toString()
        InfoPayMent.text= info.Payment
        InfoContent.text = info.Content
    }
}