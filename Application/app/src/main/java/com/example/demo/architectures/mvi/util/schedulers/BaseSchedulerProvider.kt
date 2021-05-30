package com.example.demo.architectures.mvi.util.schedulers

import io.reactivex.Scheduler

interface BaseSchedulerProvider {
  fun computation(): Scheduler
  fun io(): Scheduler
  fun ui(): Scheduler
}