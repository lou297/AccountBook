package com.pingpong.householdledger.DataClass

import java.util.*

data class DateInfo (var TimeInMilli : Long = 0, var IsHoliday: Int = 0, var Holiday: String? = null, var Income : Int = 0, var Spend : Int = 0, var Total : Int = 0)