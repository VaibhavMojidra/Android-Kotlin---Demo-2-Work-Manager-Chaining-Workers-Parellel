package com.vaibhavmojidra.androidkotlindemo2workmanagerchainingworkersparellel

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class MyWorker1(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        Thread.sleep(10000)

        return Result.success()
    }
}