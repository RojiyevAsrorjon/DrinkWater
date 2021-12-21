package uz.gita.drinkwater.presentation.fragments.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.drinkwater.R
import uz.gita.drinkwater.data.SharedPref
import uz.gita.drinkwater.databinding.WeightDialogBinding

class WeightDialog : DialogFragment(R.layout.weight_dialog) {
    private val binding by viewBinding(WeightDialogBinding::bind)
    private var listener : ((Int) -> Unit)? = null
    private val pref = SharedPref.getInstance()
    private var weight = 0
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setNumberPickerValues()
        binding.numberPicker.setOnScrollListener { numberPicker, i ->
            weight = numberPicker.value
        }
        binding.okButton.setOnClickListener {
            listener?.invoke(weight)
            dismiss()
        }
        binding.cancelButton.setOnClickListener { dismiss() }

    }
    private fun setNumberPickerValues() {
        binding.numberPicker.minValue = 25
        binding.numberPicker.maxValue = 150
        binding.numberPicker.value = pref.weight
        weight = pref.weight
    }

    fun setListener(block: (Int) -> Unit) {
        listener = block
    }

}