package uz.gita.drinkwater.presentation.fragments.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R
import uz.gita.drinkwater.data.model.GenderState
import uz.gita.drinkwater.databinding.PageSleepBinding
import uz.gita.drinkwater.presentation.viewModels.SleepScreenViewModel
import uz.gita.drinkwater.presentation.viewModels.impl.SleepScreenViewModelImpl

@AndroidEntryPoint
class SleepScreen : Fragment(R.layout.page_sleep) {
    val binding by viewBinding(PageSleepBinding::bind)
    private val viewModel: SleepScreenViewModel by viewModels<SleepScreenViewModelImpl>()

    override fun onResume() {

        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.imageLiveData.observe(viewLifecycleOwner, genderObserver)

        viewModel.updateImage()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setNumberPickersValue()
        var hourText = binding.timeHourPicker.value
        var minuteText = binding.timeMinutePicker.value
        binding.timeHourPicker.setOnScrollListener { numberPicker, pos ->
            hourText = numberPicker.value
        }
        binding.timeMinutePicker.setOnScrollListener { numberPicker, i ->
            minuteText = numberPicker.value
        }
        val time = makeTimeString(hourText, minuteText)
        viewModel.saveSleepTime(time)
    }

    private fun makeTimeString(hour: Int, minute: Int): String {
        var time = ""
        time = if (hour < 10) {
            "0$hour"
        }else{
            "$hour"
        }
        time = if (minute < 10) {
            "$time:0$minute"
        } else "$time:$minute"
        return time
    }
    private val genderObserver = Observer<Int>{
        if (it == GenderState.MALE.state) {
            binding.sleepImage.setBackgroundResource(R.drawable.boy_sleep)
        }
        else  binding.sleepImage.setBackgroundResource(R.drawable.girl_sleep)
    }
    private fun setNumberPickersValue() {
        binding.timeMinutePicker.maxValue = 59
        binding.timeHourPicker.maxValue = 23
        binding.timeHourPicker.value = 22
        binding.timeMinutePicker.value = 0
    }
}