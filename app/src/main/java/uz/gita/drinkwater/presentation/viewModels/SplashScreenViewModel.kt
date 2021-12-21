package uz.gita.drinkwater.presentation.viewModels

import androidx.lifecycle.LiveData

interface SplashScreenViewModel {
    val openMainScreenLiveData : LiveData<Int>
    val openOptionScreenLiveData : LiveData<Int>
}