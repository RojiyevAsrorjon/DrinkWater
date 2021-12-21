package uz.gita.drinkwater.data.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import uz.gita.drinkwater.R
import uz.gita.drinkwater.data.SharedPref
import java.util.*

class NotificationWorker(private val appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    private val pref = SharedPref.getInstance()
    private val calendar: Calendar = Calendar.getInstance()
    override fun doWork(): Result {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        Log.d("TTT", checkTime().toString())
        if (checkTime()) {
            setNotifications()
            pref.lastNotificationHour
        }
        return Result.success()
    }

    private fun checkTime(): Boolean {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        Log.d("TTT", "hour $hour")
        Log.d("TTT", "${pref.wakeTime.substring(0, 2).toInt()}")
        Log.d("TTT", "${pref.sleepTime.substring(0, 2).toInt()}")
        Log.d("TTT", "${pref.lastNotificationHour}")
        val wake = pref.wakeTime.substring(0, 2).toInt()
        val sleep = pref.sleepTime.substring(0, 2).toInt()
        if (sleep < wake) {
            return hour !in sleep until wake && hour != pref.lastNotificationHour
        } else {
            return hour in wake..sleep && hour != pref.lastNotificationHour
        }
    }

    private fun setNotifications() {
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("WorkManager", "Life Management", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(applicationContext, "WorkManager")
            .setContentTitle(inputData.getString("title"))
            .setContentText(inputData.getString("decs"))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
        manager.notify(1, builder.build())
    }
}