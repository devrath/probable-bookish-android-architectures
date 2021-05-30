package com.example.demo.architectures.mvi.util.schedulers

import com.example.demo.architectures.mvi.util.schedulers.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulerProvider : BaseSchedulerProvider {
  override fun computation(): Scheduler = Schedulers.computation()

  override fun io(): Scheduler = Schedulers.io()

  override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}