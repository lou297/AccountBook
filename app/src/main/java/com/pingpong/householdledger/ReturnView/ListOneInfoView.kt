package com.pingpong.householdledger.ReturnView

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pingpong.householdledger.DataClass.ExpenseInfo
import com.pingpong.householdledger.MainActivity.Companion.SpendInfoTimeFormmat
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.list_one_info_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class ListOneInfoView(context : Context, Info : ExpenseInfo) :LinearLayout(context) {
    object time{
        var cal = Calendar.getInstance()
    }
    init {
        val infalter = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        infalter.inflate(R.layout.list_one_info_view, this, false)

        time.cal.timeInMillis = Info.TimeInMilli

        InfoTime.text = SimpleDateFormat(SpendInfoTimeFormmat).format(time.cal.time)
        InfoClassification.text = Info.Classification
        InfoAmount.text = Info.Amount.toString()
        InfoPayMent.text = Info.Payment
        InfoContent.text = Info.Content

    }
}