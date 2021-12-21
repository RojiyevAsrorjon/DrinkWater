package uz.gita.drinkwater.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.drinkwater.domain.AppRepository
import uz.gita.drinkwater.presentation.viewModels.SleepScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SleepScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), SleepScreenViewModel {
    override fun saveSleepTime(time: String) {
        appRepository.saveSleepTime(time)
    }

    override val imageLiveData = MutableLiveData<Int>()
    override fun updateImage() {
        imageLiveData.value = appRepository.getGenderState()
    }

    init {
        imageLiveData.value = appRepository.getGenderState()
    }


}