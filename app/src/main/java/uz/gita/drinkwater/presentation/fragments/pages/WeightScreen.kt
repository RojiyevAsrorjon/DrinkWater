package uz.gita.drinkwater.presentation.fragments.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R
import uz.gita.drinkwater.data.model.GenderState
import uz.gita.drinkwater.databinding.PageWeightBinding
import uz.gita.drinkwater.presentation.viewModels.WeightScreenViewModel
import uz.gita.drinkwater.presentation.viewModels.impl.WeightScreenViewModelImpl

@AndroidEntryPoint
class WeightScreen : Fragment(R.layout.page_weight) {
    val binding by viewBinding(PageWeightBinding::bind)
    private val viewModel: WeightScreenViewModel by viewModels<WeightScreenViewModelImpl>()
    private var weight = 0
    override fun onResume() {
        super.onResume()
        Log.d("TTT", "onResume: ")
    }

    override fun onStart() {
        Log.d("TTT", "onStart: ")
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("TTT", "onCreateView: ")
        viewModel.imageLiveData.observe(viewLifecycleOwner, genderObserver)
        viewModel.updateImage()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("TTT", "onViewCreated: ")
        setNumberPickerValues()
        weight = binding.weightPicker.value
        binding.weightPicker.setOnScrollListener { p0, p1 ->
            weight = p0!!.value
        }

    }
    private fun setNumberPickerValues() {
        binding.weightPicker.minValue = 5
        binding.weightPicker.maxValue = 150
        binding.weightPicker.value = 60
    }
    private val genderObserver = Observer<Int>{
        if (it == GenderState.MALE.state) {
            binding.weightImage.setBackgroundResource(R.drawable.boy_weight)
        }
        else  binding.weightImage.setBackgroundResource(R.drawable.girl_weight)
    }

    override fun onPause() {
        Log.d("TTT", "onPause: ")
        super.onPause()
        viewModel.saveWeight(weight)
        viewModel.saveRecommendWaterVolume(weight)
    }

    override fun onDestroyView() {
        Log.d("TTT", "onDestroyView: ")
        super.onDestroyView()

    }
}