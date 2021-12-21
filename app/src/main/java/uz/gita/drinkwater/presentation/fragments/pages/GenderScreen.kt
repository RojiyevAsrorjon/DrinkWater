package uz.gita.drinkwater.presentation.fragments.pages

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R
import uz.gita.drinkwater.data.model.GenderState
import uz.gita.drinkwater.databinding.PageGenderBinding
import uz.gita.drinkwater.presentation.viewModels.GenderScreenViewModel
import uz.gita.drinkwater.presentation.viewModels.impl.GenderScreenViewModelImpl

@AndroidEntryPoint
class GenderScreen : Fragment(R.layout.page_gender) {
    private val binding by viewBinding(PageGenderBinding::bind)
    private var listener: ((Int) -> Unit)? = null
    private val viewModel: GenderScreenViewModel by viewModels<GenderScreenViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.femaleImage.setOnClickListener {
            viewModel.saveGender(GenderState.FEMALE.state)
            listener?.invoke(0)
        }

        binding.maleImage.setOnClickListener {
            viewModel.saveGender(GenderState.MALE.state)
            listener?.invoke(1)
        }
        observers()
    }

    private fun observers() {
        viewModel.disableFemaleButtonLiveData.observe(viewLifecycleOwner, disableFemaleButtonObserver)
        viewModel.disableMaleButtonLiveData.observe(viewLifecycleOwner, disableMaleButtonObserver)
        viewModel.enableFemaleButtonLiveData.observe(viewLifecycleOwner, enableFemaleButtonObserver)
        viewModel.enableMaleButtonLiveData.observe(viewLifecycleOwner, enableMaleButtonObserver)
    }

    private val disableMaleButtonObserver = Observer<Unit> {
        disableView(binding.maleImage, binding.textMale)
    }

    private val enableMaleButtonObserver = Observer<Unit> {
        enableView(binding.maleImage, binding.textMale)
    }

    private val disableFemaleButtonObserver = Observer<Unit> {
        disableView(binding.femaleImage, binding.textFemale)
    }

    private val enableFemaleButtonObserver = Observer<Unit> {
        enableView(binding.femaleImage, binding.textFemale)
    }

    private fun disableView(image: ImageButton, text: TextView) {
        image.alpha = 0.7f
        text.alpha = 0.7f
    }

    private fun enableView(image: ImageButton, text: TextView) {
        image.alpha = 1f
        text.alpha = 1f
    }

    fun setListener(block: ((Int) -> Unit)) {
        listener = block
    }
}

