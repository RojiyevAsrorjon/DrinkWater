package uz.gita.drinkwater.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.drinkwater.data.dao.WaterDao
import uz.gita.drinkwater.data.model.WaterClass
import javax.inject.Singleton

@Singleton
@Database(entities = [WaterClass::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao() : WaterDao

    companion object{
        private lateinit var instance : AppDatabase

        fun init(context: Context) {
            if (!Companion::instance.isInitialized) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "water_database")
                    .build()
            }
        }
        fun getDatabase() = instance
    }
}