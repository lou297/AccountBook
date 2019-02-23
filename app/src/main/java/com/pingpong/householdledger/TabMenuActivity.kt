package com.pingpong.householdledger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.pingpong.householdledger.Adapter.MainPagerAdapter
import com.pingpong.householdledger.MainActivity.Companion.PAGE
import com.pingpong.householdledger.PopUpMenu.AddRecordActivity
import kotlinx.android.synthetic.main.activity_tab_menu.*

class TabMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_menu)
        CreateTabViewPager()
        GoToAddRecordBut.setOnClickListener{
            startActivity(Intent(this, AddRecordActivity::class.java))
        }
    }

    private fun CreateTabViewPager(){
        MainMenuTabLayout.addTab(MainMenuTabLayout.newTab().setIcon(R.drawable.tab_ic_calendar).setText("달력"))
        MainMenuTabLayout.addTab(MainMenuTabLayout.newTab().setIcon(R.drawable.tab_ic_list).setText("목록"))
        MainMenuTabLayout.addTab(MainMenuTabLayout.newTab().setIcon(R.drawable.tab_ic_statistics).setText("통계"))
        MainMenuTabLayout.addTab(MainMenuTabLayout.newTab().setIcon(R.drawable.tab_ic_setting).setText("설정"))

        MainMenuViewPager.adapter = MainPagerAdapter(supportFragmentManager)
        MainMenuViewPager.offscreenPageLimit=3

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

        if(intent.hasExtra(PAGE))
            MainMenuViewPager.currentItem = intent.getIntExtra(PAGE,0)

    }
}
