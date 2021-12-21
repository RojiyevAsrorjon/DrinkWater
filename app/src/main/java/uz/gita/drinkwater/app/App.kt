package uz.gita.drinkwater.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.gita.drinkwater.data.SharedPref
import uz.gita.drinkwater.data.AppDatabase


@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        SharedPref.init(this)
        AppDatabase.init(this)
    }
}