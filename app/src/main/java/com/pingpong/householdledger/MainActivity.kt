package com.pingpong.householdledger

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import com.pingpong.householdledger.Adapter.MainPagerAdapter
import com.pingpong.householdledger.DataClass.DateInfo
import com.pingpong.householdledger.DataClass.ExpenseInfo
import com.pingpong.householdledger.DataClass.StatisticsInfo
import com.pingpong.householdledger.ReturnView.CompletedStatisticsView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    companion object {
        val YEAR = "YEAR"
        val MONTH = "MONTH"
        val DATE = "DATE"
        val MonthAndDate = "MM월 dd일"
        val SpendInfoTimeFormmat = "yyyy.MM.DD HH:mm:ss"
        var Today = Calendar.getInstance()
        val CALENDAR = "CALENDAR"
        val SPENDLIST ="SPENDLIST"
        val STATISTICS = "STATISTICS"
        val SETTING ="SETTING"
        val PAGE= "PAGE"
        val PageList = mapOf(CALENDAR to 0, SPENDLIST to 1, STATISTICS to 2, SETTING to 3)
        //달력 갯수
        val TotalCalendarFragmentNum = 25
        var CalendarYearList = mutableListOf<Int>();
        var CalendarMonthList = mutableListOf<Int>();
        var CalendarViewFragmentList : ArrayList<Fragment> = ArrayList()
        //달력 목록
        val FullList : ArrayList<DateInfo> = ArrayList()
        val DateInfoMap : HashMap<Int,Int> = HashMap();
        val StatisticsList : ArrayList<StatisticsInfo> = ArrayList()
        val StatisticsAdapterList : ArrayList<String> = arrayListOf("-")
        var StatisticsDrawDown : Int = 0
        var StatisticsTotalMoney : Int = 0
        var StatisticsStartDate = Calendar.getInstance()
        var StatisticsEndDate = Calendar.getInstance()
        val MoneyDecimalFormat = java.text.DecimalFormat("#,###")
        fun CalYear(cal : Calendar) : Int{
            return cal.get(Calendar.YEAR)
        }
        fun CalMonth(cal : Calendar) : Int{
            return cal.get(Calendar.MONTH)
        }
        fun CalDate(cal : Calendar) : Int{
            return cal.get(Calendar.DATE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SettingMainView()
        GoToCalendarBut.setOnClickListener {
            GoToViewPagerActivity(CALENDAR)
        }
        GotoListBut.setOnClickListener {
            GoToViewPagerActivity(SPENDLIST)
        }
        GotoStatisticsBut.setOnClickListener {
            GoToViewPagerActivity(STATISTICS)
        }
        GotoSettingBut.setOnClickListener {
            GoToViewPagerActivity(SETTING)
        }

    }

    override fun onResume() {
        MainPageStatisticsGroupLayout.removeAllViews()
        SettingMainView()
        super.onResume()
    }

    private fun SettingMainView(){
        val DF = SimpleDateFormat(MonthAndDate)
        MainViewDateText.text = DF.format(Today.time)
        MainPageTotalMoneyTextView.text = StatisticsTotalMoney.toString()
        for(i in StatisticsList){
            val GroupView = CompletedStatisticsView(applicationContext,i.ContentName,i.Total.toString())
            MainPageStatisticsGroupLayout.addView(GroupView)
        }
    }

    private fun GoToViewPagerActivity(Selected : String){
        val Page = PageList.get(Selected)
        val ViewPagerIntent = Intent(this,TabMenuActivity::class.java)
        ViewPagerIntent.putExtra(PAGE, Page)
        startActivity(ViewPagerIntent)
    }


}
