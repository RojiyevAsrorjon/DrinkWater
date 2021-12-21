package uz.gita.drinkwater.presentation.fragments

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R
import uz.gita.drinkwater.databinding.ScreenSettingBinding
import uz.gita.drinkwater.presentation.fragments.dialog.GenderDialog
import uz.gita.drinkwater.presentation.fragments.dialog.WeightDialog
import uz.gita.drinkwater.presentation.viewModels.SettingScreenViewModel
import uz.gita.drinkwater.presentation.viewModels.impl.SettingScreenViewModelImpl

@AndroidEntryPoint
class SettingScreen : Fragment(R.layout.screen_setting) {
    private val binding by viewBinding(ScreenSettingBinding::bind)
    private val viewModel: SettingScreenViewModel by viewModels<SettingScreenViewModelImpl>()
    private var hour = 0
    private var minute = 0
    private var bedHour = 0
    private var bedMinute = 0
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.genderFrame.setOnClickListener {
            val dialog = GenderDialog()
            dialog.setListener {
                viewModel.updateGender(it)
            }
            activity?.supportFragmentManager?.let { it1 -> dialog.show(it1, "dialog_gender") }
        }
        binding.weightFrame.setOnClickListener {
            val dialog = WeightDialog()
            dialog.setListener {
                viewModel.updateWeight(it)
                viewModel.setRecommendVolume(it)
            }
            activity?.supportFragmentManager?.let { it1 -> dialog.show(it1, "dialog_weight") }
        }

        binding.wakeUpFrame.setOnClickListener {
            var time = ""
            val timePicker = TimePickerDialog(requireContext(), { p0, hour, minute ->
                var hourString = ""
                var minuteString = ""
                hourString = if (hour < 10) {
                    "0$hour"
                } else "$hour"
                minuteString = if (minute < 10) {
                    "0$minute"
                } else "$minute"
                time += "$hourString:$minuteString"
                viewModel.updateWakeUpTime(time)
            },hour, minute, true)
            timePicker.show()
        }
        binding.bedTimeFrame.setOnClickListener {
            var time = ""
            val timePicker = TimePickerDialog(requireContext(), { p0, hour, minute ->
                var hourString = ""
                var minuteString = ""
                hourString = if (hour < 10) {
                    "0$hour"
                } else "$hour"
                minuteString = if (minute < 10) {
                    "0$minute"
                } else "$minute"
                time += "$hourString:$minuteString"
                viewModel.updateBedTime(time)
            },bedHour, bedMinute, true)
            timePicker.show()
        }

        binding.backButton.setOnClickListener { findNavController().navigateUp() }

        binding.resetFrame.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Reset data")
                .setMessage("Reset plans and delete all existing data")
                .setPositiveButton("Ok") { p0, p1 ->
                    viewModel.resetAllData()
                }
                .setNegativeButton("Cancel"){ p0, p1 ->

                }
                .show()
        }
        observers()
    }

    private fun observers() {
        viewModel.genderLivedata.observe(viewLifecycleOwner, genderObserver)
        viewModel.weightLiveData.observe(viewLifecycleOwner, weightObserver)
        viewModel.wakeUpLiveData.observe(viewLifecycleOwner, wakeUpTimeObserver)
        viewModel.bedTimeLiveData.observe(viewLifecycleOwner, bedTimeObserver)
        viewModel.hourLiveData.observe(viewLifecycleOwner, hourObserver)
        viewModel.minuteLiveData.observe(viewLifecycleOwner, minuteObserver)
        viewModel.sleepTimeLiveData.observe(viewLifecycleOwner, sleepTimeObserver)
        viewModel.openOptionScreenLiveData.observe(viewLifecycleOwner, openOptionScreenObserver)
    }

    private val genderObserver = Observer<String> {
        binding.gender.text = it
    }
    private val weightObserver = Observer<String> {
        binding.weight.text = it
    }
    private val wakeUpTimeObserver = Observer<String> {
        binding.wakeUpTime.text = it
    }
    private val bedTimeObserver = Observer<String> {
        binding.bedTime.text = it
    }

    private val hourObserver = Observer<Int>{
        hour = it
    }
    private val minuteObserver = Observer<Int>{
        minute = it
    }
    private val sleepTimeObserver = Observer<Pair<Int, Int>>{
        bedHour = it.first
        bedMinute = it.second
    }
    private val openOptionScreenObserver = Observer<Unit>{
        findNavController().navigate(R.id.optionScreen)
    }

}