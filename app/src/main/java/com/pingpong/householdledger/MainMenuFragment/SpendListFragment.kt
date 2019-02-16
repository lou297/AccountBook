package com.pingpong.householdledger.MainMenuFragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.pingpong.householdledger.Adapter.SpendListRecylerViewAdapter
import com.pingpong.householdledger.MainActivity.Companion.FullList
import com.pingpong.householdledger.R
import kotlinx.android.synthetic.main.main_frag_spend_list.*

class SpendListFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        CallList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_frag_spend_list,container,false)
    }

    override fun onResume() {
        super.onResume()
        val adapter = SpendListRecylerViewAdapter(context!!,FullList)
        SpendListRecylerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun CallList(){
        SpendListRecylerView.layoutManager = LinearLayoutManager(context)
        SpendListRecylerView.adapter = SpendListRecylerViewAdapter(context!!,FullList)

    }
}