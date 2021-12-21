package uz.gita.drinkwater.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.work.*
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.drinkwater.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    private lateinit var myWorkRequest: OneTimeWorkRequest
//    private lateinit var myWorkRequestP : PeriodicWorkRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val navigation = supportFragmentManager.findFragmentById(R.id.container1) as NavHostFragment
        val graph = navigation.navController.navInflater.inflate(R.navigation.app_nav)
        navigation.navController.graph = graph


//        val navigation = supportFragmentManager.findFragmentById(R.id.container1) as NavHostFragment
//        val graph = navigation.navController.navInflater.inflate(R.navigation.app_nav)
//        navigation.navController.graph = graph
//        findViewById<Button>(R.id.buttonCreate).setOnClickListener {
//            myWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
//                .setInitialDelay(30, TimeUnit.SECONDS)
//                .setInputData(workDataOf("title" to "salom", "decs" to "demo notification"))
//                .build()
//            WorkManager.getInstance(this).enqueue(myWorkRequest)
//            Toast.makeText(this, "created", Toast.LENGTH_SHORT).show()
//        }
//        val data = Data.Builder()
//        data.putInt("sdad", 2)
//        findViewById<Button>(R.id.buttonCreate).setOnClickListener {
//            myWorkRequestP = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
//                .setInputData(data.build())
//                .build()
////                .setInputData(workDataOf("title" to "salom", "decs" to "demo notification"))
//            WorkManager.getInstance(this).enqueue(myWorkRequestP)
//            Toast.makeText(this, "created", Toast.LENGTH_SHORT).show()
//        }
//
//        findViewById<Button>(R.id.buttonUpdate).setOnClickListener {
//            myWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
//                .setInitialDelay(30, TimeUnit.SECONDS)
//                .setInputData(workDataOf("title" to "Assalomu alekum", "decs" to "Vaalekum Assalom"))
//                .build()
//            WorkManager.getInstance(this).enqueue(myWorkRequest)
//        }
//        findViewById<Button>(R.id.buttonCancel).setOnClickListener {
//            WorkManager.getInstance(this).cancelWorkById(myWorkRequest.id)
//            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
//        }

    }
}