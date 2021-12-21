package uz.gita.drinkwater.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R
import uz.gita.drinkwater.presentation.viewModels.CreatePlanScreenViewModel
import uz.gita.drinkwater.presentation.viewModels.impl.CreatePlanScreenViewModelImpl

@AndroidEntryPoint
class CreatePlanScreen : Fragment(R.layout.screen_plan_create) {
    private val viewModel : CreatePlanScreenViewModel by viewModels<CreatePlanScreenViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.init()
        viewModel.openMainScreenLiveData.observe(viewLifecycleOwner, openNextScreenObserver)
    }
    private val openNextScreenObserver = Observer<Unit>{
        findNavController().navigate(R.id.action_createPlanScreen_to_screenMain)
    }
}