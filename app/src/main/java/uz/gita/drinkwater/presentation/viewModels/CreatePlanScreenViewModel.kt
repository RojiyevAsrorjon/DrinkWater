package uz.gita.drinkwater.presentation.viewModels

import androidx.lifecycle.LiveData

interface CreatePlanScreenViewModel {
    val openMainScreenLiveData : LiveData<Unit>

    fun init()
}