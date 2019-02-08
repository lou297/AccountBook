package com.pingpong.householdledger.DataClass

data class DateInfo (val Year : Int, val Month : Int, val Date: Int, var IsHoliday: Int = 0, var Holiday: String? = null, var Income : Int = 0, var Spend : Int = 0, var Total : Int = 0)