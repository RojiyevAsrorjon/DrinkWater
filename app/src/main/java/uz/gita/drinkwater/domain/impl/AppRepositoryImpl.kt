package uz.gita.drinkwater.domain.impl

import android.annotation.SuppressLint
import android.text.format.DateUtils
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import uz.gita.drinkwater.data.AppDatabase
import uz.gita.drinkwater.data.RecommendDatabase
import uz.gita.drinkwater.data.SharedPref
import uz.gita.drinkwater.data.model.ScreenState
import uz.gita.drinkwater.data.model.WaterClass
import uz.gita.drinkwater.domain.AppRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.random.Random


class AppRepositoryImpl @Inject constructor() : AppRepository {
    private val appDatabase = AppDatabase.getDatabase()
    private val sharedPref = SharedPref.getInstance()
    private val recommendDatabase = RecommendDatabase()
    private val list = ArrayList<WaterClass>()
    private var drunkWater = 0
    override fun getScreenState(): Int {
        return sharedPref.screenState
    }

    override fun getGenderState(): Int = sharedPref.gender
    override fun getWeight(): Int = sharedPref.weight
    override fun getWakeUpTime(): String {
        return sharedPref.wakeTime
    }

    override fun getBedTime(): String {
        return sharedPref.sleepTime
    }

    override fun saveGenderState(gender: Int) {
        sharedPref.gender = gender
    }

    override fun saveWakeTime(time: String) {
        sharedPref.wakeTime = time
    }

    override fun saveSleepTime(time: String) {
        sharedPref.sleepTime = time
    }

    override fun saveWeight(weight: Int) {
        sharedPref.weight = weight
    }

    override fun updateScreenStateToMain() {
        sharedPref.screenState = ScreenState.MAIN.state
    }

    override fun updateScreenStateToOption() {
        sharedPref.screenState = ScreenState.OPTION.state
    }

    override suspend fun addDrunkWater(day: String) {
        val volume = sharedPref.cupVolume
        appDatabase.dao().insert(WaterClass(0, volume, System.currentTimeMillis(), day))
    }

    override fun getTodayDrunkWaterList(day: String): Flow<List<WaterClass>> = appDatabase.dao().getTodayDrunkWater(day)


    override fun getRecommendedWaterVolume() = sharedPref.requestedVolume

    override fun setWaterAmountByWeight(weight: Int) {
        val volume: Int = (weight * 0.033 * 1000).toInt()
        sharedPref.requestedVolume = volume
    }


    override fun getNextAdvice(): String {
        val pos = sharedPref.advicesPosition
        val recommend = recommendDatabase.listOfRecommends[pos]
        var newPos = Random.nextInt(0, recommendDatabase.listOfRecommends.size)
        sharedPref.advicesPosition = newPos
        return recommend
    }

    override fun getCurrentCupVolume(): Int {
        return sharedPref.cupVolume
    }

    override fun setCurrentCupVolume(volume: Int) {
        sharedPref.cupVolume = volume
    }

//    override fun getProgressBarPercent(coroutineScope: CoroutineScope, day: String): Flow<Int> = flow{
//        getData(coroutineScope, day)
//        val volume = sharedPref.requestedVolume
//        val percent = ((drunkWater.toFloat()/volume.toFloat())*100).toInt()
//        Log.d("TTT", "Percent $percent")
//        emit(percent)
//    }

    override suspend fun delete(waterClass: WaterClass) {
        appDatabase.dao().delete(waterClass)
    }

    override suspend fun deleteAll() = appDatabase.clearAllTables()
    override fun getNextTime() : String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        var st = ""
        st = if (hour < 9) {
            "0${hour+1}"
        } else "${hour+1}"
        return "$st:00"
    }


//    override fun getDrunkWaterAmount(coroutineScope: CoroutineScope, day: String): Flow<Int> = flow{
//        getData(coroutineScope, day)
//        emit(drunkWater)
//    }
//    private fun getData(coroutineScope: CoroutineScope, day: String) {
//        coroutineScope.launch(Dispatchers.IO) {
//            appDatabase.dao().getTodayDrunkWater(day).collect {
//                list.clear()
//                list.addAll(it)
//                drunkWater = 0
//                for (i in list) {
//                    drunkWater+=i.volume
//                }
//            }
//        }
//    }
}