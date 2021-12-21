package uz.gita.drinkwater.presentation.viewModels

import androidx.lifecycle.LiveData

interface WakeScreenViewModel {
    fun saveWakeTime(time : String)
    val imageLiveData : LiveData<Int>
    fun updateImage()
}