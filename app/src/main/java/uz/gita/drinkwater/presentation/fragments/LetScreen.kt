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
import uz.gita.drinkwater.databinding.ScreenLetBinding
import uz.gita.drinkwater.presentation.viewModels.LetScreenViewModel
import uz.gita.drinkwater.presentation.viewModels.impl.LetScreenViewModelImpl

@AndroidEntryPoint
class LetScreen : Fragment(R.layout.screen_let) {
    private val binding by viewBinding(ScreenLetBinding::bind)
    private val viewModel: LetScreenViewModel by viewModels<LetScreenViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.letButton.setOnClickListener { viewModel.init() }

        viewModel.openOptionScreenLiveData.observe(viewLifecycleOwner, observer)
    }

    private val observer = Observer<Unit>{
        navController.navigate(R.id.action_letScreen_to_optionScreen)
    }
}