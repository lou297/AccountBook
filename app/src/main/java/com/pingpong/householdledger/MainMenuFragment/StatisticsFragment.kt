package com.pingpong.householdledger.MainMenuFragment

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.pingpong.householdledger.DataClass.StatisticsInfo
import com.pingpong.householdledger.MainActivity.Companion.StatisticsAdapterList
import com.pingpong.householdledger.MainActivity.Companion.StatisticsList
import com.pingpong.householdledger.MainActivity.Companion.StatisticsTotalMoney
import com.pingpong.householdledger.R
import com.pingpong.householdledger.ReturnView.CompletedStatisticsView
import com.pingpong.householdledger.ReturnView.StatisticsView
import kotlinx.android.synthetic.main.main_frag_statistics.*
import kotlinx.android.synthetic.main.statistics_sub_group.*

class StatisticsFragment : Fragment(){
    var EditTextView = GONE
    var TotalMoneyText = VISIBLE

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //뷰 미리 생성
        val SubGroup = StatisticsView(context!!)
        StatisticsGroupLayout.addView(SubGroup)
        RemoveView(SubGroup)
        //싱글톤으로 생성해주려면??
////////////////////////////////////////////////
        ///////////////////////싱글톤으로 바꿔 줘야됨
        AddGroupBut.setOnClickListener {
            AddGroupFun(SubGroup)
        }
        EditTotalMoneyBut.setOnClickListener {
            EditTotalMoney()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_frag_statistics,container,false)
    }
    private fun CreateCompletedView(layout : LinearLayout){
        StatisticsGroupLayout.addView(layout)
    }

    private fun CreateView(layout : LinearLayout){
        layout.visibility = VISIBLE
    }
    private fun RemoveView(layout : LinearLayout){
        layout.visibility = GONE
    }

    private fun AddGroupFun(SubGroup : LinearLayout){
        if(EditTextView==GONE){
            CreateView(SubGroup)
            EditTextView = VISIBLE
            CreateSubBut.setOnClickListener {
                RemoveView(SubGroup)
                EditTextView = GONE
                AddStatisticsContent(SubGroupEditContent.text.toString(),SubGroupEditMoney.text.toString())
                SubGroupEditContent.setText("")
                SubGroupEditMoney.setText("")
//                    CreateCompletedView(CompletedStatisticsView(context!!,SubGroupEditContent.text.toString(),SubGroupEditMoney.text.toString()))
            }
        }
    }

    private fun AddStatisticsContent(content : String, total : String){
        val IntTotal = Integer.parseInt(total)
        var Content = StatisticsInfo(ContentName = content, Total = IntTotal)
        StatisticsList.add(Content)
        StatisticsAdapterList.add(content)
        CreateCompletedView(CompletedStatisticsView(context!!,content,total))
    }

    private fun EditTotalMoney(){
        when(TotalMoneyText){
            VISIBLE -> {
                TotalMoneyText = GONE
                TotalMoneyTextView.visibility = GONE
                TotalMoneyEditText.visibility = VISIBLE
            }
            GONE -> {

                try {
                    StatisticsTotalMoney = TotalMoneyEditText.text.toString().toInt()
                } catch (e:Exception){
                    Toast.makeText(context,"올바른 숫자를 입력해 주세요.",Toast.LENGTH_LONG)
                }
                TotalMoneyText = VISIBLE
                TotalMoneyTextView.text = StatisticsTotalMoney.toString()
                TotalMoneyEditText.visibility = GONE
                TotalMoneyTextView.visibility = VISIBLE
            }
        }
    }


}