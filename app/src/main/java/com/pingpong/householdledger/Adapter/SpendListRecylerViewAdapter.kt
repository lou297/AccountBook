package com.pingpong.householdledger.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pingpong.householdledger.DataClass.ExpenseInfo
import com.pingpong.householdledger.MainActivity.Companion.CalMonth
import com.pingpong.householdledger.MainActivity.Companion.CalYear
import com.pingpong.householdledger.MainActivity.Companion.DateInfoList
import com.pingpong.householdledger.R
import com.pingpong.householdledger.ReturnView.SpendListDateView
import kotlinx.android.synthetic.main.list_one_day_view.view.*
import kotlinx.android.synthetic.main.list_one_info_view.view.*
import java.util.*

class SpendListRecylerViewAdapter (val context : Context, val expenseList : ArrayList<ExpenseInfo>): RecyclerView.Adapter<SpendListRecylerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.list_one_info_view,parent,false))
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        return holder.bind(expenseList.get(position) ,position)
    }


    inner class Holder(view : View) : RecyclerView.ViewHolder(view){
        val DateLinearLayout = view.DateLinearLayout
        val Time = view.InfoTime
        val Classification = view.InfoClassification
        val Amount =view.InfoAmount
        val Payment = view.InfoPayMent
        val Content = view.InfoContent
        fun bind( Info : ExpenseInfo, position : Int){
            for(i in DateInfoList){
                if(Info.DateInLength8==i.DateInLength8){
                    Log.d("test","zzzzzzzzzzzzzzzz")
                    DateLinearLayout.addView(SpendListDateView(context, i))
                }
            }
            Time.text = Info.DateInLength8.toString()
            Classification.text = Info.Classification
            Amount.text = Info.Amount.toString()
            Log.d("test","zzzzzzzzzzzzzzzz")
            Payment.text = Info.Payment
            Content.text = Info.Content
            Log.d("test","zzzzzzzzzzzzzzzz")
        }

    }
}