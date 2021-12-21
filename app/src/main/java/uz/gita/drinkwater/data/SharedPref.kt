package uz.gita.drinkwater.data

import android.content.Context
import uz.gita.drinkwater.data.model.GenderState
import uz.gita.drinkwater.data.model.ScreenState
import java.util.*

class SharedPref private constructor(context: Context){

    companion object {
        private lateinit var instance : SharedPref
        fun init(context: Context) {
            instance = SharedPref(context)
        }
        fun getInstance() = instance
    }
    private val pref = context.getSharedPreferences("drinkWater", Context.MODE_PRIVATE)

    var screenState : Int
        get() = pref.getInt("screenState",ScreenState.OPTION.state)
        set(value) {
            pref.edit().putInt("screenState", value).apply()
        }

    var lastNotificationHour : Int
        get() = pref.getInt("lastNotificationHour", 6)
        set(value) { pref.edit().putInt("lastNotificationHour", value) }

    var gender : Int
        get() = pref.getInt("genderState", GenderState.MALE.state)
        set(value) { pref.edit().putInt("genderState", value).apply() }

    var weight : Int
        get() = pref.getInt("weight", 60)
        set(value) { pref.edit().putInt("weight", value).apply() }

    var sleepTime : String
        get() = pref.getString("sleepTime", "22:00")!!
        set(value) { pref.edit().putString("sleepTime", value).apply() }

    var wakeTime : String
        get() = pref.getString("wakeTime", "6:00")!!
        set(value) { pref.edit().putString("wakeTime", value).apply() }

    var requestedVolume : Int
        get() = pref.getInt("requestedVolume",1000)
        set(value) { pref.edit().putInt("requestedVolume", value).apply() }

    var drunkWaterVolume : Int
        get() = pref.getInt("drunkWaterVolume",0)
        set(value) { pref.edit().putInt("drunkWaterVolume", value).apply() }


    var advicesPosition : Int
        get() = pref.getInt("advicesPosition",0)
        set(value) { pref.edit().putInt("advicesPosition", value).apply() }

    var cupVolume : Int
        get() = pref.getInt("cupVolume", 200)
        set(value) { pref.edit().putInt("cupVolume", value).apply() }
}