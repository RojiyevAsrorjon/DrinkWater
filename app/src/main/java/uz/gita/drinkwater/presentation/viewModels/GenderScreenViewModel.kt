package uz.gita.drinkwater.presentation.viewModels

import androidx.lifecycle.LiveData

interface GenderScreenViewModel {
    val genderScreenLiveData: LiveData<Int>

    val disableMaleButtonLiveData: LiveData<Unit>
    val enableMaleButtonLiveData: LiveData<Unit>

    val disableFemaleButtonLiveData: LiveData<Unit>
    val enableFemaleButtonLiveData: LiveData<Unit>

    fun saveGender(gender: Int)
}