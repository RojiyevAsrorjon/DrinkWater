package uz.gita.drinkwater.presentation.viewModels.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.drinkwater.data.model.ScreenState
import uz.gita.drinkwater.domain.AppRepository
import uz.gita.drinkwater.presentation.viewModels.SplashScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), SplashScreenViewModel {
    override val openMainScreenLiveData = MutableLiveData<Int>()
    override val openOptionScreenLiveData = MutableLiveData<Int>()

    init {
        viewModelScope.launch{
            delay(1700)
            Log.d("TTT",appRepository.getScreenState().toString() )
            if (appRepository.getScreenState() == ScreenState.OPTION.state) {
                openOptionScreenLiveData.value = ScreenState.OPTION.state
            }
            else openMainScreenLiveData.value = ScreenState.MAIN.state
        }
    }

}