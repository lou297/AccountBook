package com.pingpong.householdledger.MainMenuFragment

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.pingpong.householdledger.DataClass.StatisticsInfo
import com.pingpong.householdledger.MainActivity.Companion.MoneyDecimalFormat
import com.pingpong.householdledger.MainActivity.Companion.StatisticsAdapterList
import com.pingpong.householdledger.MainActivity.Companion.StatisticsList
import com.pingpong.householdledger.MainActivity.Companion.StatisticsTotalMoney
import com.pingpong.householdledger.R
import com.pingpong.householdledger.ReturnView.CompletedStatisticsView
import kotlinx.android.synthetic.main.main_frag_statistics.*

class StatisticsFragment : Fragment(){
    var SubGroupEditText = GONE
    var TotalMoneyText = VISIBLE
    var TotalMoneyString = ""
    var SubGroupMoneyString =""
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //뷰 미리 생성
        //싱글톤으로 생성해주려면??
////////////////////////////////////////////////
        ///////////////////////싱글톤으로 바꿔 줘야됨
        SettingStatisticsView()

        AddGroupBut.setOnClickListener {
            AddGroupFun()
        }
        EditTotalMoneyBut.setOnClickListener {
            EditTotalMoney()
        }

        TotalMoneyEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!TextUtils.isEmpty(s.toString()) && !s.toString().equals(TotalMoneyString)) {
                    TotalMoneyString = MoneyDecimalFormat.format((s.toString().replace(",","")).toDouble())
                    TotalMoneyEditText.setText(TotalMoneyString)
                    TotalMoneyEditText.setSelection(TotalMoneyString.length)
                }
            }

        })

        SubGroupMoneyEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!TextUtils.isEmpty(s.toString()) && !s.toString().equals(SubGroupMoneyString)) {
                    SubGroupMoneyString = MoneyDecimalFormat.format((s.toString().replace(",","")).toDouble())
                    SubGroupMoneyEditText.setText(SubGroupMoneyString)
                    SubGroupMoneyEditText.setSelection(SubGroupMoneyString.length)
                }
            }

        })



    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_frag_statistics,container,false)
    }

    override fun onResume() {
        super.onResume()
        SettingStatisticsView()
    }

    private fun CreateCompletedView(layout : LinearLayout){
        StatisticsGroupLayout.addView(layout)
    }


    private fun SettingStatisticsView(){
        TotalMoneyTextView.setText(StatisticsTotalMoney.toString())
        StatisticsGroupLayout.removeAllViews()

        for(i in StatisticsList){
            CreateCompletedView(CompletedStatisticsView(context!!,i.ContentName,i.Total.toString()))
        }
    }

    private fun AddGroupFun(){
        if(SubGroupEditText==GONE){
            SubGroupLayout.visibility = VISIBLE
            SubGroupEditText = VISIBLE
            CreateSubBut.setOnClickListener {

                val ContentText = SubGroupContentEditText.text.toString()
                val MoneyText = SubGroupMoneyEditText.text.toString()
                if(ContentText.length<1 || MoneyText.length<4){
                    Toast.makeText(context,"내역을 1글자 이상, 금액을 1,000원 이상 입력해주세요.",Toast.LENGTH_SHORT).show()
                }
                else{
                    SubGroupLayout.visibility = GONE
                    SubGroupEditText = GONE
                    AddStatisticsContent(SubGroupContentEditText.text.toString(),SubGroupMoneyEditText.text.toString())
                    SubGroupContentEditText.setText("")
                    SubGroupMoneyEditText.setText("")
                }

            }
        }
    }

    private fun AddStatisticsContent(content : String, total : String){
        val IntTotal = Integer.parseInt(total.replace(",",""))
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
                val MoneyText = TotalMoneyEditText.text.toString().replace(",","")
                if(MoneyText.length<4){
                    Toast.makeText(context,"1,000원 이상을 입력해주세요.",Toast.LENGTH_SHORT).show()
                } else{
                    try {
                        StatisticsTotalMoney = MoneyText.toInt()
                    } catch (e:Exception){
                        Toast.makeText(context,"올바른 숫자를 입력해 주세요.",Toast.LENGTH_SHORT)
                    }
                    TotalMoneyText = VISIBLE
                    TotalMoneyTextView.text = MoneyText
                    TotalMoneyEditText.visibility = GONE
                    TotalMoneyTextView.visibility = VISIBLE
                }

            }
        }
    }


}