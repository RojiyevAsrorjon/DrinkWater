package uz.gita.drinkwater.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R
import uz.gita.drinkwater.databinding.ScreenDrinkBinding

@AndroidEntryPoint
class RecommendScreen : Fragment(R.layout.screen_drink) {
    private val binding by viewBinding(ScreenDrinkBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backButton.setOnClickListener { findNavController().navigateUp() }
    }
}