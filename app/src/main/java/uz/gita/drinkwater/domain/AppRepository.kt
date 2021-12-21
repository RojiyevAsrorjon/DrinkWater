package uz.gita.drinkwater.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.drinkwater.data.model.WaterClass

interface AppRepository {
    fun getScreenState() : Int
    fun getGenderState() : Int
    fun getWeight() : Int
    fun getWakeUpTime() : String
    fun getBedTime() : String

    fun saveGenderState(gender: Int)
    fun saveWakeTime(time: String)
    fun saveSleepTime(time : String)
    fun saveWeight(weight : Int)

    fun updateScreenStateToMain()
    fun updateScreenStateToOption()

    suspend fun addDrunkWater(day : String)
    fun getTodayDrunkWaterList(day : String) : Flow<List<WaterClass>>
    fun getRecommendedWaterVolume(): Int
    fun setWaterAmountByWeight(weight : Int)
//    fun getDrunkWaterAmount(coroutineScope: CoroutineScope, day : String) : Flow<Int>
    fun getNextAdvice(): String

    fun getCurrentCupVolume() : Int
    fun setCurrentCupVolume(volume : Int)
//    fun getProgressBarPercent(coroutineScope: CoroutineScope, day : String) : Flow<Int>

    suspend fun delete(waterClass: WaterClass)

    suspend fun deleteAll()

    fun getNextTime() : String
}