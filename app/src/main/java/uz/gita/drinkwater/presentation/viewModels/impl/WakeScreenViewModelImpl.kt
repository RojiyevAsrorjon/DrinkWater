package uz.gita.drinkwater.presentation.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.drinkwater.domain.AppRepository
import uz.gita.drinkwater.presentation.viewModels.WakeScreenViewModel
import javax.inject.Inject

@HiltViewModel
class WakeScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), WakeScreenViewModel {
    override fun saveWakeTime(time: String) {
        appRepository.saveWakeTime(time)
    }

    override val imageLiveData = MutableLiveData<Int>()
    override fun updateImage() {
        imageLiveData.value = appRepository.getGenderState()

    }

    init {
        imageLiveData.value = appRepository.getGenderState()
    }
}