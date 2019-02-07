package com.pingpong.householdledger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.View
import com.pingpong.householdledger.Adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        val MonthAndDate = "MM월 dd일"
        val CALENDAR = "CALENDAR"
        val LIST ="LIST"
        val DETAIL ="DETAIL"
        val PAGE= "PAGE"
        val PageList = mapOf(CALENDAR to 0, LIST to 1, DETAIL to 2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SettingMainView()

        GoToCalendarBut.setOnClickListener {
            GoToViewPagerActivity(CALENDAR)
        }
        GotoListBut.setOnClickListener {
            GoToViewPagerActivity(LIST)
        }
        GotoDetailBut.setOnClickListener {
            GoToViewPagerActivity(DETAIL)
        }

    }

    private fun SettingMainView(){
        val DF = SimpleDateFormat(MonthAndDate)
        val calendar = Calendar.getInstance()

        MainViewDateText.text = DF.format(calendar.time)

    }

    private fun GoToViewPagerActivity(Selected : String){
        val Page = PageList.get(Selected)
        val ViewPagerIntent = Intent(this,TabMenuActivity::class.java)
        ViewPagerIntent.putExtra(PAGE, Page)
        startActivity(ViewPagerIntent)
    }
}
