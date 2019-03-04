package com.pingpong.householdledger.ReturnView

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pingpong.householdledger.MainActivity.Companion.MoneyDecimalFormat
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.statistics_sub_group_created.view.*

class CompletedStatisticsView(context : Context , Content: String , TotalMoney : Int ,DrawDown : Int) : LinearLayout(context){
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.statistics_sub_group_created,this,true)
        SubGroupContent.text = Content
        SubGroupTotalMoney.text = MoneyDecimalFormat.format(TotalMoney)
        SubGroupDrawDownMoney.text = MoneyDecimalFormat.format(DrawDown)
        StatisticsProgressBar.max = TotalMoney
        StatisticsProgressBar.progress = DrawDown
    }
}