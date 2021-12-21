package uz.gita.drinkwater.presentation.viewModels.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.gita.drinkwater.data.model.WaterClass
import uz.gita.drinkwater.domain.AppRepository
import uz.gita.drinkwater.presentation.convertToString
import uz.gita.drinkwater.presentation.viewModels.MainScreenLiveData
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MainScreenLiveDataImpl @Inject constructor(private val appRepository: AppRepository) : ViewModel(), MainScreenLiveData {
    override val drunkWaterListLiveData = MutableLiveData<List<WaterClass>>()
    override val recommendTextLiveData = MutableLiveData<String>()
    override val drunkWaterVolumeLiveData = MutableLiveData<Int>()
    override val recommendWaterAmountLiveData=  MutableLiveData<Int>()
    override val progressBarLiveData = MutableLiveData<Int>()
    override val currentCupVolumeLiveData = MutableLiveData<Int>()
    override val notificationTimeLiveData = MutableLiveData<String>()
    private var day = ""
    private var recommendWaterAmount = 0
    init {
        day = convertToString(System.currentTimeMillis())
        recommendWaterAmount = appRepository.getRecommendedWaterVolume()
        recommendWaterAmountLiveData.value = recommendWaterAmount
        viewModelScope.launch(Dispatchers.IO) {

            appRepository.getTodayDrunkWaterList(day).collect {
                drunkWaterListLiveData.postValue(it)
                progressBarLiveData.postValue((calculate(it as ArrayList<WaterClass>).toFloat() / recommendWaterAmount.toFloat() * 100).toInt())
                drunkWaterVolumeLiveData.postValue(calculate(it))
            }
//            appRepository.getDrunkWaterAmount(viewModelScope, day).collect {
//                drunkWaterVolumeLiveData.postValue(it)
//                val percent = ((it.toFloat()/recommendWaterAmount.toFloat())*100).toInt()
//                progressBarLiveData.postValue(percent)
//            }

        }
        currentCupVolumeLiveData.value = appRepository.getCurrentCupVolume()
        recommendTextLiveData.value = appRepository.getNextAdvice()

        notificationTimeLiveData.value = appRepository.getNextTime()
    }

    private fun calculate(list: ArrayList<WaterClass>): Int {
        var amount = 0
        if (list.size==0) {
            return 0
        }
        for (i in list) {
            amount+=i.volume
        }
        return amount
    }

    override fun addDrunkWater() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.addDrunkWater(day)
        }
    }

    override fun updateRecommendPosition() {
        viewModelScope.launch {
            delay(10_000)
            recommendTextLiveData.value = appRepository.getNextAdvice()
        }
    }

    override fun setCurrentCupVolume(volume: Int) {
        appRepository.setCurrentCupVolume(volume)
        currentCupVolumeLiveData.value = volume
    }

    override fun delete(waterClass: WaterClass) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.delete(waterClass)
        }
    }

    override fun updateRecommendWater() {
        recommendWaterAmount = appRepository.getRecommendedWaterVolume()
        recommendWaterAmountLiveData.value = recommendWaterAmount
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.getTodayDrunkWaterList(day).collect {
                drunkWaterListLiveData.postValue(it)
                progressBarLiveData.postValue((calculate(it as ArrayList<WaterClass>).toFloat() / recommendWaterAmount.toFloat() * 100).toInt())
                drunkWaterVolumeLiveData.postValue(calculate(it))
            }
        }
    }


}