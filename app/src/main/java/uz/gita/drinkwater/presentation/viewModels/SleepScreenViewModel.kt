package uz.gita.drinkwater.presentation.viewModels

import androidx.lifecycle.LiveData

interface SleepScreenViewModel {
    fun saveSleepTime(time : String)
    val imageLiveData : LiveData<Int>
    fun updateImage()
}