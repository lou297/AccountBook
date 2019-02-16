package com.pingpong.householdledger.DataClass

import java.util.*
import kotlin.collections.ArrayList

data class DateInfo (val DateInLength8 : Int = 0,var TimeInMilli : Long = 0, var IsHoliday: Int = 0, var Holiday: String? = null, var Income : Int = 0, var Spend : Int = 0, var Total : Int = 0 , val ExpenseList : ArrayList<ExpenseInfo> = ArrayList())