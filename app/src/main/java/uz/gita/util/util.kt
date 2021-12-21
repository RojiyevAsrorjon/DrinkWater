package uz.gita.drinkwater.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs


@RequiresApi(Build.VERSION_CODES.N)
fun htmltext(html: String?): String {
    return android.text.Html.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}

fun Fragment.hideKeyBoard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

@SuppressLint("SimpleDateFormat")
fun convertToData(time: String): Long {
    try {
        val dateFormat = SimpleDateFormat("dd/mm/yyyy HH:mm")
        val date = dateFormat.parse(time)
        return date.time
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return 0L
}

fun convertToDate(time: Long): String {
//    val date = Date(time)
//    return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date)

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time


    val hour =
        if (calendar.get(Calendar.HOUR) < 10) "0${calendar.get(Calendar.HOUR)}" else "${calendar.get(Calendar.HOUR)}"
    val minute =
        if (calendar.get(Calendar.MINUTE) < 10) "0${calendar.get(Calendar.MINUTE)}" else "${calendar.get(Calendar.MINUTE)}"

    return "$hour:$minute"
}

fun convertToString(time: Long): String {
//    val date = Date(time)
//    return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date)

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time

    val year = "${calendar.get(Calendar.YEAR)}"
    val month =
        if (calendar.get(Calendar.MONTH) < 9) "0${calendar.get(Calendar.MONTH) + 1}" else "${calendar.get(Calendar.MONTH) + 1}"
    val day = if (calendar.get(Calendar.DAY_OF_MONTH) < 10) "0${calendar.get(Calendar.DAY_OF_MONTH)}" else "${
        calendar.get(Calendar.DAY_OF_MONTH)
    }"

    return "$day/$month/$year"
}


fun diffDate(time1: Long, time2: Long): Long {

    var diff = abs(time1 - time2)
    val diffInDays = TimeUnit.MILLISECONDS.toDays(diff)
    return diffInDays
}

fun diffMill(time1: String, time2: Long): Long {
    val t1 = convertToData(time1)
    val t2 = time2
    return abs(t1 - t2)
}

fun isOldNote(time1: String, time2: String): Boolean {
    val t1 = convertToData(time1)
    val t2 = convertToData(time2)
    return t1 < t2
}

fun showAgo(time: Long): String {
    var difference: Long = 0
    val mDate = System.currentTimeMillis()

    if (mDate > time) {
        difference = mDate - time
        val seconds = difference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val months = days / 31
        val years = days / 365
        return if (seconds < 0) {
            "not yet"
        } else if (seconds < 60) {
            "moments ago"
        } else if (seconds < 120) {
            "a minute ago"
        } else if (seconds < 2700) // 45 * 60
        {
            "$minutes minutes ago"
        } else if (seconds < 5400) // 90 * 60
        {
            "an hour ago"
        } else if (seconds < 86400) // 24 * 60 * 60
        {
            "$hours hours ago"
        } else if (seconds < 172800) // 48 * 60 * 60
        {
            "yesterday"
        } else if (seconds < 2592000) // 30 * 24 * 60 * 60
        {
            "$days days ago"
        } else if (seconds < 31104000) // 12 * 30 * 24 * 60 * 60
        {
            if (months <= 1) "one month ago" else "$days months ago"
        } else {
            if (years <= 1) "one year ago" else "$years years ago"
        }
    }
    return ""
}

