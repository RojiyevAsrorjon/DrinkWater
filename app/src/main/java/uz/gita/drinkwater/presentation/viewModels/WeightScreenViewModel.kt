package uz.gita.drinkwater.presentation.viewModels

import androidx.lifecycle.LiveData

interface WeightScreenViewModel {
    fun saveWeight(weight : Int)
    fun saveRecommendWaterVolume(weight : Int)
    val imageLiveData : LiveData<Int>
    fun updateImage()
}