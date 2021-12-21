package uz.gita.drinkwater.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R
import uz.gita.drinkwater.databinding.ScreenOptionsBinding
import uz.gita.drinkwater.presentation.adapter.PageAdapter
import uz.gita.drinkwater.presentation.fragments.pages.GenderScreen
import uz.gita.drinkwater.presentation.fragments.pages.SleepScreen
import uz.gita.drinkwater.presentation.fragments.pages.WakeScreen
import uz.gita.drinkwater.presentation.fragments.pages.WeightScreen
import uz.gita.drinkwater.presentation.viewModels.OptionScreenViewModel
import uz.gita.drinkwater.presentation.viewModels.impl.OptionScreenViewModelImpl
import uz.gita.util.DepthPageTransformer

@AndroidEntryPoint
class OptionScreen : Fragment(R.layout.screen_options) {
    private val genderScreen = GenderScreen()
    private val weightScreen = WeightScreen()
    private val wakeScreen = WakeScreen()
    private val sleepScreen = SleepScreen()
    private val binding by viewBinding(ScreenOptionsBinding::bind)
    private lateinit var adapter: PageAdapter
    private var isWeight = false
    private var isWake = false
    private var isSleep = false
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val viewModel: OptionScreenViewModel by viewModels<OptionScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = activity?.supportFragmentManager?.let {
            PageAdapter(
                it,
                genderScreen,
                weightScreen,
                wakeScreen,
                sleepScreen,
                lifecycle
            )
        }!!
        setView()
        genderScreen.setListener {
            if (isWeight)
                if (it == 1) {
                    weightScreen.binding.let {
                        it.weightImage.setBackgroundResource(R.drawable.boy_weight)
                    }
                } else {
                    weightScreen.binding.let {
                        it.weightImage.setBackgroundResource(R.drawable.girl_weight)
                    }
                }
            if (isWake)
                if (it == 1) {
                    wakeScreen.binding.let {
                        it.sleepImage.setBackgroundResource(R.drawable.boy_wake)
                    }
                } else {
                    wakeScreen.binding.let {
                        it.sleepImage.setBackgroundResource(R.drawable.girl_wake)
                    }
                }

            if (isSleep)
                if (it == 1) {
                    sleepScreen.binding.let {
                        it.sleepImage.setBackgroundResource(R.drawable.boy_sleep)
                    }
                } else {
                    sleepScreen.binding.let {
                        it.sleepImage.setBackgroundResource(R.drawable.girl_sleep)
                    }
                }

        }
    }

    private fun setView() {
        val tabTextList = listOf("Gender", "Weight", "Wake", "Sleep")
        val tabIcons = listOf(
            R.drawable.ic_gender,
            R.drawable.ic_weight,
            R.drawable.ic_wake,
            R.drawable.ic_sleep
        )
        val viewPager = binding.viewPager
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = true
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.d("TTT", "onPageScrolled: ")
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.d("TTT", "onPageScrollStateChanged: ")
                super.onPageScrollStateChanged(state)
            }

            override fun onPageSelected(position: Int) {
                if (position == 1) isWeight = true
                if (position == 2) isWake = true
                if (position == 3) isSleep = true
                Log.d("TTT", "onPageSelected: ")
                super.onPageSelected(position)
            }
        })
        viewPager.setPageTransformer(DepthPageTransformer())
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.setIcon(tabIcons[pos])
            tab.text = tabTextList[pos]
        }.attach()

        binding.nextButton.setOnClickListener {
            isWeight = true
            if (viewPager.currentItem < 3) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                navController.navigate(R.id.action_optionScreen_to_createPlanScreen)
                viewModel.updateScreenState()
            }
        }
        binding.backButton.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }
    }
}
