package uz.gita.drinkwater.presentation.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.drinkwater.presentation.viewModels.CreatePlanScreenViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePlanScreenViewModelImpl @Inject constructor() : ViewModel(), CreatePlanScreenViewModel {
    override val openMainScreenLiveData = MutableLiveData<Unit>()

    override fun init() {
        viewModelScope.launch {
            delay(4200)
            openMainScreenLiveData.value = Unit
        }
    }
}