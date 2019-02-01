package com.pingpong.householdledger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.pingpong.householdledger.Adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CreateView()
    }

    private fun CreateView(){
        MainMenuTabLayout.addTab(MainMenuTabLayout.newTab().setIcon(R.drawable.tab_ic_calendar).setText("달력"))
        MainMenuTabLayout.addTab(MainMenuTabLayout.newTab().setIcon(R.drawable.tab_ic_list).setText("목록"))
        MainMenuTabLayout.addTab(MainMenuTabLayout.newTab().setIcon(R.drawable.tab_ic_setting).setText("설정"))

        MainMenuViewPager.adapter = MainPagerAdapter(supportFragmentManager)

        MainMenuViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(MainMenuTabLayout))
        MainMenuTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab) {
                MainMenuViewPager.currentItem = p0.position
            }
        })
    }
}
