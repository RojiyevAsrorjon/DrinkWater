package uz.gita.drinkwater.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.drinkwater.presentation.viewModels.LetScreenViewModel
import javax.inject.Inject

@HiltViewModel
class LetScreenViewModelImpl @Inject constructor(): ViewModel(), LetScreenViewModel {
    override val openOptionScreenLiveData = MutableLiveData<Unit>()

    override fun init() {
        openOptionScreenLiveData.value = Unit
    }
}