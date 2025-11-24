package com.example.bancodigital.util

import java.text.DateFormat
import java.text.DateFormat.getDateTimeInstance
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class GetMask {

    companion object {

        const val DAY_MONTH = 0
        const val DAY_MONTH_YEAR = 1
        const val HOUR_MINUTE = 2
        const val DAY_MONTH_YEAR_HOUR_MINUTE = 3

        fun getFormatedDate(date: Long, type: Int): String {
            val locale = Locale("en", "GB")
            val fuso = "Europa/Portugal"

            val daySdf = SimpleDateFormat("dd", locale)
            daySdf.timeZone = TimeZone.getTimeZone(fuso)

            val monthSdf = SimpleDateFormat("MM", locale)
            monthSdf.timeZone = TimeZone.getTimeZone(fuso)

            val yearSdf = SimpleDateFormat("yyyy", locale)
            yearSdf.timeZone = TimeZone.getTimeZone(fuso)

            val hourSdf = SimpleDateFormat("HH", locale)
            hourSdf.timeZone = TimeZone.getTimeZone(fuso)

            val minuteSdf = SimpleDateFormat("mm", locale)
            minuteSdf.timeZone = TimeZone.getTimeZone(fuso)

            val dateFormat: DateFormat = getDateTimeInstance()
            val netDate = Date(date)
            dateFormat.format(netDate)

            val hour: String = hourSdf.format(netDate)
            val minute: String = minuteSdf.format(netDate)
            val day: String = daySdf.format(netDate)
            val month: String = monthSdf.format(netDate)
            val year: String = yearSdf.format(netDate)

            val time: String = when (type) {
                DAY_MONTH_YEAR -> "$day/$month/$year"
                HOUR_MINUTE -> "$hour:$minute"
                DAY_MONTH_YEAR_HOUR_MINUTE -> "$day/$month/$year $hour:$minute"
                DAY_MONTH -> "$day/$month"
                else -> "WRONG"
            }
            return time
        }

        fun getFormatedValue(value: Float): String? {
            val nf: NumberFormat = DecimalFormat(
                "#.##0.00", DecimalFormatSymbols(
                    Locale("en", "GB")
                )
            )
            return nf.format(value)
        }

    }

}