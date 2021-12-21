package uz.gita.drinkwater.presentation.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.drinkwater.domain.AppRepository
import uz.gita.drinkwater.presentation.viewModels.OptionScreenViewModel
import javax.inject.Inject

@HiltViewModel
class OptionScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), OptionScreenViewModel {
    override fun updateScreenState() {
        appRepository.updateScreenStateToMain()
    }
}