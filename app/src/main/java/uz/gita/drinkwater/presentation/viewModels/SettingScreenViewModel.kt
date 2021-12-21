package uz.gita.drinkwater.presentation.viewModels

import androidx.lifecycle.LiveData

interface SettingScreenViewModel {
    val genderLivedata: LiveData<String>
    val weightLiveData : LiveData<String>
    val wakeUpLiveData : LiveData<String>
    val bedTimeLiveData : LiveData<String>
    val hourLiveData : LiveData<Int>
    val minuteLiveData : LiveData<Int>
    val sleepTimeLiveData : LiveData<Pair<Int, Int>>
    val openOptionScreenLiveData : LiveData<Unit>

    fun updateGender(gender: Int)
    fun updateWeight(weight: Int)
    fun updateWakeUpTime(time: String)
    fun updateBedTime(time : String)
    fun resetAllData()
    fun setRecommendVolume(weight : Int)

}