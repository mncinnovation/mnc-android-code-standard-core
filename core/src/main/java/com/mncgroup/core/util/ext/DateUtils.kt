@file:Suppress("unused")

package com.mncgroup.core.util.ext

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

val indonesianLocale = Locale("in", "ID")
const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
const val DOB_FORMAT = "yyyy-MM-dd"
const val DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss"
const val READABLE_DOB_FORMAT = "d MMMM yyyy"
const val SUCCESS_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"
const val TEMP_FILE_FORMAT = "yyyyMMdd_HHmmss"
val readableDobFormat = SimpleDateFormat(READABLE_DOB_FORMAT, indonesianLocale)
val dobFormat = SimpleDateFormat(DOB_FORMAT, indonesianLocale)
val dateTimeFormat = SimpleDateFormat(DATE_TIME_FORMAT, indonesianLocale)
val apiDateFormat = SimpleDateFormat(API_DATE_FORMAT, indonesianLocale)
val readableDateFormat = SimpleDateFormat("EEEE, MMMM d", indonesianLocale)
val readableTimeFormat = SimpleDateFormat("HH:mm", indonesianLocale)
val readableDateTimeFormat = SimpleDateFormat("EEEE, MMMM d HH:mm", indonesianLocale)
val successDateFormat = SimpleDateFormat(SUCCESS_DATE_FORMAT, indonesianLocale)
val tempFileFormat = SimpleDateFormat(TEMP_FILE_FORMAT, indonesianLocale)

fun Calendar.getDate() = this[Calendar.DAY_OF_MONTH]
fun Calendar.getMonth() = this[Calendar.MONTH]
fun Calendar.getFullYear() = this[Calendar.YEAR]
fun Calendar.getHours() = this[Calendar.HOUR_OF_DAY]
fun Calendar.getMinutes() = this[Calendar.MINUTE]
fun Calendar.getSeconds() = this[Calendar.SECOND]
fun Calendar.getMilliseconds() = this[Calendar.MILLISECOND]

fun DateFormat.parseIndonesianCalendar(dateString: String): Calendar =
    Calendar.getInstance(indonesianLocale).apply {
        val parsedDate = this@parseIndonesianCalendar.parse(dateString)
        if (parsedDate != null) {
            time = parsedDate
        }
    }

fun Date.toSuccessString(): String = successDateFormat.format(this)

fun SimpleDateFormat.formatCalendar(calendar: Calendar): String = this.format(calendar.time)

fun Int.asMinutesString(): String = if (this < 10) "0$this" else toString()

fun Int.secondToMinuteSecondString(): String {
    val minute = this / 60
    val second = this % 60
    return "${minute.asMinutesString()}:${second.asMinutesString()}"
}

fun Context.showDatePickerAction(
    @StyleRes style: Int? = null,
    initYear: Int? = null,
    actionListener: (day: Int, month: Int, year: Int) -> Unit
): DatePickerDialog = this.let {
    val currentTime = Calendar.getInstance()
    val yearInit = initYear ?: currentTime.get(Calendar.YEAR)
    val monthInit = currentTime.get(Calendar.MONTH)
    val day = currentTime.get(Calendar.DAY_OF_MONTH)
    if (style != null) DatePickerDialog(
        this, style, { _, year, month, dayOfMonth ->
            actionListener(dayOfMonth, month + 1, year)
        }, yearInit, monthInit, day
    ) else DatePickerDialog(
        this, { _, year, month, dayOfMonth ->
            actionListener(dayOfMonth, month + 1, year)
        }, yearInit, monthInit, day
    )
}