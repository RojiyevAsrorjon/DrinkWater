package uz.gita.drinkwater.presentation.viewModels

import androidx.lifecycle.LiveData
import uz.gita.drinkwater.data.model.WaterClass

interface MainScreenLiveData {
    val drunkWaterListLiveData : LiveData<List<WaterClass>>
    val recommendTextLiveData : LiveData<String>
    val drunkWaterVolumeLiveData : LiveData<Int>
    val recommendWaterAmountLiveData : LiveData<Int>
    val progressBarLiveData : LiveData<Int>
    val currentCupVolumeLiveData : LiveData<Int>
    val notificationTimeLiveData : LiveData<String>
    fun addDrunkWater()
    fun updateRecommendPosition()
    fun setCurrentCupVolume(volume : Int)
    fun delete(waterClass: WaterClass)
    fun updateRecommendWater()
}