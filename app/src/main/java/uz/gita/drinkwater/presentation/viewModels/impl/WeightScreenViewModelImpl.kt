package uz.gita.drinkwater.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.drinkwater.domain.AppRepository
import uz.gita.drinkwater.presentation.viewModels.WeightScreenViewModel
import javax.inject.Inject

@HiltViewModel
class WeightScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), WeightScreenViewModel{
    override val imageLiveData = MutableLiveData<Int>()
    override fun updateImage() {
        imageLiveData.value = appRepository.getGenderState()
    }

    init {
        imageLiveData.value = appRepository.getGenderState()
    }

    override fun saveWeight(weight: Int) {
        appRepository.saveWeight(weight)
    }

    override fun saveRecommendWaterVolume(weight: Int) {
        appRepository.setWaterAmountByWeight(weight)
    }
}