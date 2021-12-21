package uz.gita.drinkwater.presentation.fragments.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.drinkwater.R
import uz.gita.drinkwater.data.SharedPref
import uz.gita.drinkwater.data.model.GenderState
import uz.gita.drinkwater.databinding.GenderDialogBinding

class GenderDialog : DialogFragment(R.layout.gender_dialog) {
    private var listener : ((Int) -> Unit)? = null
    private val pref = SharedPref.getInstance()
    private val binding by viewBinding(GenderDialogBinding::bind)
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val group = binding.group
        val index = pref.gender
        if (index == GenderState.MALE.state) {
            group.check(R.id.radio2)
        }
        else{
            group.check(R.id.radio1)
        }
        binding.okButton.setOnClickListener {
            when (group.checkedRadioButtonId) {
                R.id.radio1 -> listener?.invoke(GenderState.FEMALE.state)
                R.id.radio2 -> listener?.invoke(GenderState.MALE.state)
            }
            dismiss()
        }
        binding.cancelButton.setOnClickListener { dismiss() }
    }
    fun setListener(block: (Int) -> Unit) {
        listener = block
    }
}