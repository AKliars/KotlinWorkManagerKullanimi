package com.akliars.workmanagerkullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.akliars.workmanagerkullanimi.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        binding.buttonYap.setOnClickListener {

            val calismaKosulu = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()



         /*   val istek = OneTimeWorkRequestBuilder<MyWorker>()
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(calismaKosulu)
                .build()*/

            val istek = PeriodicWorkRequestBuilder<MyWorkerBildirim>(15,TimeUnit.MINUTES)
                .setInitialDelay(5,TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(this).enqueue(istek)

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(istek.id)
                .observe(this){
                    val durum = it.state.name
                    Log.e("Arkaplan Durum",durum)
                }
        }



        setContentView(binding.root)
    }
}