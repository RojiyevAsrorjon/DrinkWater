package uz.gita.drinkwater.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R
import uz.gita.drinkwater.databinding.ScreenSplashBinding
import uz.gita.drinkwater.presentation.viewModels.SplashScreenViewModel
import uz.gita.drinkwater.presentation.viewModels.impl.SplashScreenViewModelImpl

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openMainScreenLiveData.observe(viewLifecycleOwner, screenMainObserver)
        viewModel.openOptionScreenLiveData.observe(viewLifecycleOwner, screenOptionObserver)
    }

    private val screenMainObserver = Observer<Int>{
        navController.navigate(R.id.action_splashScreen_to_screenMain)
    }

    private val screenOptionObserver = Observer<Int>{
        navController.navigate(R.id.action_splashScreen_to_letScreen)
    }
}