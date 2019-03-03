package com.pingpong.householdledger.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.DataClass.ExpenseInfo
import com.pingpong.householdledger.MainActivity.Companion.CalMonth
import com.pingpong.householdledger.MainActivity.Companion.CalYear
import com.pingpong.householdledger.R
import com.pingpong.householdledger.ReturnView.SpendListDateView
import kotlinx.android.synthetic.main.list_one_day_view.view.*
import kotlinx.android.synthetic.main.list_one_info_view.view.*
import java.util.*
import kotlin.collections.ArrayList

class SpendListRecylerViewAdapter (val context : Context, val FullList : List<DateInfo>): RecyclerView.Adapter<SpendListRecylerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.list_one_day_view,parent,false))
    }

    override fun getItemCount(): Int {
        return FullList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        return holder.bind(FullList.get(position) ,position)
    }


    inner class Holder(view : View) : RecyclerView.ViewHolder(view){
        val DateLinearLayout = view.DateLinearLayout
        val Time = view.ListDate
        val Income = view.OneDayTotalIncome
        val Spend =view.OneDayTotalSpend
        val Money = view.OneDayTotalMoney

//        val DynamicalList = ArrayList<SpendListDateView>()
        //각각의 지출내역 뷰를 담은 리스트
        fun bind( Info : DateInfo, position : Int){
            Time.text = Info.DateInLength8.toString()
            Income.text = Info.Income.toString()
            Spend.text = Info.Spend.toString()
            Money.text = Info.Total.toString()
            for(i in Info.ExpenseList){
                val OneDateView = SpendListDateView(context,i)
                DateLinearLayout.addView(OneDateView)
//                DynamicalList.add(OneDateView)
            }
        }
    }
}