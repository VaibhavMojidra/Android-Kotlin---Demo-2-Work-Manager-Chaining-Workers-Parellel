package com.vaibhavmojidra.androidkotlindemo2workmanagerchainingworkersparellel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.vaibhavmojidra.androidkotlindemo2workmanagerchainingworkersparellel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val oneTimeWorkRequestForWorker1=OneTimeWorkRequest.Builder(MyWorker1::class.java).build()
        val oneTimeWorkRequestForWorker2=OneTimeWorkRequest.Builder(MyWorker2::class.java).build()

        val workManager= WorkManager.getInstance(this)

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestForWorker1.id).observe(this) {
            binding.work1Textview.text=it.state.name
        }

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestForWorker2.id).observe(this) {
            binding.work2Textview.text=it.state.name
        }

        val listOfWorkers= mutableListOf<OneTimeWorkRequest>()
        listOfWorkers.add(oneTimeWorkRequestForWorker1)
        listOfWorkers.add(oneTimeWorkRequestForWorker2)

        binding.startWorksButton.setOnClickListener {
            workManager.enqueue(listOfWorkers)
        }
    }
}