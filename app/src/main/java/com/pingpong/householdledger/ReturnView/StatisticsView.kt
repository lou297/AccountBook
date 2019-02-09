package com.pingpong.householdledger.ReturnView

import android.app.ActionBar
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.pingpong.householdledger.R

class StatisticsView(context : Context) : LinearLayout(context) {
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.statistics_sub_group,this,true)
    }



}