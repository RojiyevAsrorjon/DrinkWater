package uz.gita.drinkwater.presentation.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.drinkwater.data.model.GenderState
import uz.gita.drinkwater.domain.AppRepository
import uz.gita.drinkwater.presentation.viewModels.SettingScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), SettingScreenViewModel {
    override val genderLivedata = MutableLiveData<String>()
    override val weightLiveData = MutableLiveData<String>()
    override val wakeUpLiveData = MutableLiveData<String>()
    override val bedTimeLiveData = MutableLiveData<String>()
    override val hourLiveData = MutableLiveData<Int>()
    override val minuteLiveData = MutableLiveData<Int>()
    override val sleepTimeLiveData = MutableLiveData<Pair<Int, Int>>()
    override val openOptionScreenLiveData = MutableLiveData<Unit>()

    init {
        genderLivedata.value = if (appRepository.getGenderState() == GenderState.MALE.state)
                "Male" else "Female"
        weightLiveData.value = "${appRepository.getWeight()} kg"
        wakeUpLiveData.value = appRepository.getWakeUpTime()
        bedTimeLiveData.value = appRepository.getBedTime()
        hourLiveData.value = appRepository.getWakeUpTime().split(":")[0].toInt()
        minuteLiveData.value = appRepository.getWakeUpTime().split(":")[1].toInt()
        sleepTimeLiveData.value = Pair(appRepository.getBedTime().split(":")[0].toInt(), appRepository.getBedTime().split(":")[1].toInt())
    }

    override fun updateGender(gender: Int) {
        appRepository.saveGenderState(gender)
        genderLivedata.value = if (appRepository.getGenderState() == GenderState.MALE.state)
            "Male" else "Female"
    }

    override fun updateWeight(weight: Int) {
        appRepository.saveWeight(weight)
        weightLiveData.value = "${appRepository.getWeight()} kg"
    }

    override fun updateWakeUpTime(time: String) {
        appRepository.saveWakeTime(time)
        wakeUpLiveData.value = appRepository.getWakeUpTime()
    }

    override fun updateBedTime(time: String) {
        appRepository.saveSleepTime(time)
        bedTimeLiveData.value = appRepository.getBedTime()
    }

    override fun resetAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteAll()
        }
        appRepository.updateScreenStateToOption()
        openOptionScreenLiveData.value = Unit

    }

    override fun setRecommendVolume(weight: Int) {
        appRepository.setWaterAmountByWeight(weight)
    }
}