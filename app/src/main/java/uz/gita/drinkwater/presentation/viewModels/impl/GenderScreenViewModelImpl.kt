package uz.gita.drinkwater.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.drinkwater.data.model.GenderState
import uz.gita.drinkwater.domain.AppRepository
import uz.gita.drinkwater.presentation.viewModels.GenderScreenViewModel
import javax.inject.Inject

@HiltViewModel
class GenderScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), GenderScreenViewModel {
    override val genderScreenLiveData = MutableLiveData<Int>()
    override val disableMaleButtonLiveData = MutableLiveData<Unit>()
    override val enableMaleButtonLiveData = MutableLiveData<Unit>()
    override val disableFemaleButtonLiveData = MutableLiveData<Unit>()
    override val enableFemaleButtonLiveData = MutableLiveData<Unit>()

    init {
        if (appRepository.getGenderState() == GenderState.MALE.state) {
            enableMaleButtonLiveData.value = Unit
            disableFemaleButtonLiveData.value = Unit
        }
        else{
            disableMaleButtonLiveData.value = Unit
            enableFemaleButtonLiveData.value = Unit
        }
    }

    override fun saveGender(gender: Int) {
        if (gender == GenderState.MALE.state) {
            enableMaleButtonLiveData.value = Unit
            disableFemaleButtonLiveData.value = Unit
        }
        else{
            disableMaleButtonLiveData.value = Unit
            enableFemaleButtonLiveData.value = Unit
        }
        appRepository.saveGenderState(gender)
    }

}