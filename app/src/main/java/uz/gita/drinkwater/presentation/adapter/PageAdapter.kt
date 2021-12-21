package uz.gita.drinkwater.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.drinkwater.presentation.fragments.pages.GenderScreen
import uz.gita.drinkwater.presentation.fragments.pages.SleepScreen
import uz.gita.drinkwater.presentation.fragments.pages.WakeScreen
import uz.gita.drinkwater.presentation.fragments.pages.WeightScreen

class PageAdapter(
    activity: FragmentManager,
    private val genderScreen: GenderScreen,
    private val weightScreen: WeightScreen,
    private val wakeScreen: WakeScreen,
    private val sleepScreen: SleepScreen,
    lifecycle: Lifecycle
) : FragmentStateAdapter(activity, lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> genderScreen
            1 -> weightScreen
            2 -> wakeScreen
            else -> sleepScreen
        }
    }
}