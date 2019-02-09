package com.pingpong.householdledger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.activity_tab_menu.*

class AddRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        CancelBut.setOnClickListener {
            finish()
        }
    }
}
