package com.pingpong.householdledger.MainMenuFragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.pingpong.householdledger.R
import com.pingpong.householdledger.ReturnView.CompletedStatisticsView
import com.pingpong.householdledger.ReturnView.StatisticsView
import kotlinx.android.synthetic.main.main_frag_statistics.*
import kotlinx.android.synthetic.main.statistics_sub_group.*

class StatisticsFragment : Fragment(){
    var EditTextView = GONE

    companion object {
        var GroupList : HashMap<String,Int> = HashMap()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //뷰 미리 생성
        val SubGroup = StatisticsView(context!!)
        StatisticsGroupLayout.addView(SubGroup)
        RemoveView(SubGroup)
        //싱글톤으로 생성해주려면??

        AddGroupBut.setOnClickListener {
            if(EditTextView==GONE){
                CreateView(SubGroup)
                EditTextView = VISIBLE
                CreateSubBut.setOnClickListener {
                    RemoveView(SubGroup)
                    EditTextView = GONE
                    CreateCompletedView(CompletedStatisticsView(context!!,SubGroupEditContent.text.toString(),SubGroupEditMoney.text.toString()))
                }
            }

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
}