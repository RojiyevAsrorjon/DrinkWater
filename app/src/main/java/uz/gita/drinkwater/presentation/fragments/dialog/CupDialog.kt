package uz.gita.drinkwater.presentation.fragments.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.drinkwater.R
import uz.gita.drinkwater.data.SharedPref
import uz.gita.drinkwater.databinding.ScreenDialogBinding

class CupDialog : DialogFragment(R.layout.screen_dialog) {
    private val pref = SharedPref.getInstance()
    private var listener: ((Int) -> Unit)? = null
    private val binding by viewBinding(ScreenDialogBinding::bind)
    private val listOfViews = ArrayList<ImageButton>()
    private val listOfVolumes = ArrayList<Int>()
    private var selectedPos = -1
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addViews()
        loadData()
        for ((index, view) in listOfViews.withIndex()) {
            view.setOnClickListener {
                if (selectedPos != index) {
                    listOfViews[selectedPos].setBackgroundResource(R.drawable.bg_unselected)
                }
                 view.setBackgroundResource(R.drawable.bg_selected)
                selectedPos = index
            }
        }

        binding.okButton.setOnClickListener {
            listener?.invoke(listOfVolumes[selectedPos])
            pref.cupVolume = listOfVolumes[selectedPos]
            dismiss()
        }
        binding.cancelButton.setOnClickListener { dismiss() }
    }

    private fun addViews() {
        listOfViews.addAll(
            listOf(
                binding.cup1,
                binding.cup2,
                binding.cup3,
                binding.cup4,
                binding.cup5,
                binding.cup6
            )
        )
    }

    fun setListener(block: (Int) -> Unit) {
        listener = block
    }

    private fun loadData() {
        listOfVolumes.addAll(
            listOf(
                100, 150, 175, 200, 300, 400
            )
        )
        Log.d("TTT", "Pref cup ${pref.cupVolume}")
        val index = listOfVolumes.indexOfFirst { it == pref.cupVolume }
        Log.d("TTT", "Pref index $index")
        listOfViews[index].setBackgroundResource(R.drawable.bg_selected)
        selectedPos = index
    }
}