package uz.gita.drinkwater.presentation.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R
import uz.gita.drinkwater.data.model.NotificationWorker
import uz.gita.drinkwater.data.model.WaterClass
import uz.gita.drinkwater.databinding.ScreenMainBinding
import uz.gita.drinkwater.presentation.adapter.RecyclerAdapter
import uz.gita.drinkwater.presentation.fragments.dialog.CupDialog
import uz.gita.drinkwater.presentation.viewModels.MainScreenLiveData
import uz.gita.drinkwater.presentation.viewModels.impl.MainScreenLiveDataImpl
import java.lang.reflect.Method
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val adapter = RecyclerAdapter()
    private var amount = 0
    private var recommendWaterVolume = 0
    private var y = 0f
    private val viewModel: MainScreenLiveData by viewModels<MainScreenLiveDataImpl>()

    override fun onResume() {
        super.onResume()
        viewModel.updateRecommendWater()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter
        val manager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = manager
        manager.stackFromEnd = true
        manager.reverseLayout = true
        binding.settingButton.setOnClickListener {
            findNavController().navigate(R.id.settingScreen)
        }
        binding.addAnimation.post {
            y = binding.addAnimation.y
        }

        adapter.setItemListener { data, view ->
            val popUpMenu = PopupMenu(requireContext(), view)
            popUpMenu.menuInflater.inflate(R.menu.pop_menu, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.delete -> {

                        viewModel.delete(data)
                    }
                }
                true
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popUpMenu.setForceShowIcon(true)
            } else {
                try {
                    val fields = popUpMenu.javaClass.declaredFields
                    for (field in fields) {
                        if ("mPopup" == field.name) {
                            field.isAccessible = true
                            val menuPopupHelper = field[popUpMenu]
                            val classPopupHelper =
                                Class.forName(menuPopupHelper.javaClass.name)
                            val setForceIcons: Method = classPopupHelper.getMethod(
                                "setForceShowIcon",
                                Boolean::class.javaPrimitiveType
                            )
                            setForceIcons.invoke(menuPopupHelper, true)
                            break
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            popUpMenu.show()
        }
        binding.lampButton.setOnClickListener {
            findNavController().navigate(R.id.recommendScreen)
        }

        binding.cupImage.setOnClickListener {
            viewModel.addDrunkWater()
            binding.recyclerView.smoothScrollToPosition(adapter.itemCount)
            binding.cupImage.isClickable = false
            binding.addAnimation.text = "+$amount ml"
            binding.addAnimation.alpha = 1f
            binding.addAnimation.visibility = View.VISIBLE
            binding.addAnimation.animate().setDuration(2000).alpha(0f).translationY(-50f).withEndAction {
                binding.addAnimation.y = y
                binding.cupImage.isClickable = true
            }.start()
        }


        binding.recyclerButton.setOnClickListener {
            val dialog = CupDialog()
            dialog.setListener {
                viewModel.setCurrentCupVolume(it)
            }
            activity?.supportFragmentManager?.let { it1 -> dialog.show(it1, "cup_dialog") }
        }
        startWorkManager()
        observers()
    }

    private fun startWorkManager() {
        val workManager = PeriodicWorkRequestBuilder<NotificationWorker>(2, TimeUnit.HOURS)
            .setInputData(workDataOf("title" to "Drink Water", "decs" to "Time for a glass of water"))
            .build()
        WorkManager.getInstance(requireContext()).enqueue(workManager)
    }

    private fun observers() {
        viewModel.drunkWaterListLiveData.observe(viewLifecycleOwner, drunkWaterListObserver)
        viewModel.recommendTextLiveData.observe(viewLifecycleOwner, recommendTextObserver)
        viewModel.recommendWaterAmountLiveData.observe(viewLifecycleOwner, recommendWaterViewModel)
        viewModel.drunkWaterVolumeLiveData.observe(viewLifecycleOwner, drunkWaterVolumeObserver)
        viewModel.progressBarLiveData.observe(viewLifecycleOwner, progressBarObserver)
        viewModel.currentCupVolumeLiveData.observe(viewLifecycleOwner, currentCupVolumeObserver)
        viewModel.notificationTimeLiveData.observe(viewLifecycleOwner, nextTimeObserver)
    }

    private fun noteIsEmpty(list: List<WaterClass>) {
        binding.notYetText.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        binding.notYetText.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }


    private val recommendTextObserver = Observer<String> {
        binding.recommendText.animate().setDuration(2500).alpha(0.5f)
            .withEndAction {
                binding.recommendText.alpha = 0f
                binding.recommendText.text = it
                binding.recommendText.animate().setDuration(2500).alpha(1f).start()
                viewModel.updateRecommendPosition()
            }
            .start()
    }

    private val drunkWaterListObserver = Observer<List<WaterClass>> {
        adapter.submitList(it)
        noteIsEmpty(it)

    }

    private val currentCupVolumeObserver = Observer<Int> {
        binding.currentCupVolume.text = it.toString()
        binding.timeRecommendVolume.text = it.toString()
        amount = it

    }

    private val recommendWaterViewModel = Observer<Int> {
        val amountString = " / ${it}ml"
        binding.amountRequestedWater.text = amountString
        recommendWaterVolume = it
    }

    private val drunkWaterVolumeObserver = Observer<Int> {
        binding.amountDrunkWater.text = it.toString()
    }

    private val progressBarObserver = Observer<Int> {
        binding.progressBar.setPercent(it)
    }
    private val nextTimeObserver = Observer<String>{
        binding.timeTime.text = it
    }

    override fun onPause() {
        super.onPause()
        binding.recommendText.animate().cancel()
    }
}