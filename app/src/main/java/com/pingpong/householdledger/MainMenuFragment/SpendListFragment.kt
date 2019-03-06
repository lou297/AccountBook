package com.pingpong.householdledger.MainMenuFragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.animation.AnimationUtils
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.LinearLayout
import com.pingpong.householdledger.Adapter.SpendListRecylerViewAdapter
import com.pingpong.householdledger.MainActivity
import com.pingpong.householdledger.MainActivity.Companion.FullList
import com.pingpong.householdledger.MainActivity.Companion.StatisticsList
import com.pingpong.householdledger.MainActivity.Companion.ViewList
import com.pingpong.householdledger.R
import com.pingpong.householdledger.ReturnView.CompletedStatisticsView
import kotlinx.android.synthetic.main.main_frag_spend_list.*

class SpendListFragment : Fragment() {

    private var Sliding=0
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        CallList()

        //애니메이션
        val SlidingUp = android.view.animation.AnimationUtils.loadAnimation(context,R.anim.sliding_up)
        val SlidingDown = android.view.animation.AnimationUtils.loadAnimation(context,R.anim.sliding_down)

        SlidingStatisticsButton.setOnClickListener {
            if(Sliding==0){
                SetUpSlidingView()
                SlidingUp.setAnimationListener(SlidingAnimation())
                SlidingStatistics.startAnimation(SlidingUp)
                SlidingStatistics.visibility = VISIBLE
                Sliding=1
            }
            else{
                SlidingDown.setAnimationListener(SlidingAnimation())
                SlidingStatistics.startAnimation(SlidingDown)
                SlidingStatistics.visibility = GONE
                Sliding=0
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_frag_spend_list,container,false)
    }

    override fun onResume() {
        super.onResume()
        MainActivity().ArrangeList()
        val adapter = SpendListRecylerViewAdapter(context!!, ViewList)
        SpendListRecylerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private inner class SlidingAnimation : Animation.AnimationListener{
        override fun onAnimationRepeat(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
        }

        override fun onAnimationStart(animation: Animation?) {
        }

    }

    private fun CallList(){
        MainActivity().ArrangeList()
        SpendListRecylerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        SpendListRecylerView.adapter = SpendListRecylerViewAdapter(context!!,ViewList)

    }

    private fun SetUpSlidingView(){
        SlidingStatistics.removeAllViews()
        StatisticsList.forEach {
            SlidingStatistics.addView(CompletedStatisticsView(context!!,it.ContentName,it.Total,it.DrawDown))
        }
    }
}