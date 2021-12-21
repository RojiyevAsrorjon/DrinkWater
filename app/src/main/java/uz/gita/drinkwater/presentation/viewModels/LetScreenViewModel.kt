package uz.gita.drinkwater.presentation.viewModels

import androidx.lifecycle.LiveData

interface LetScreenViewModel {
    val openOptionScreenLiveData : LiveData<Unit>

    fun init()
}