package com.loadBalancer.model

import kotlinx.coroutines.*

data class Provider(val id: String, var load: Int = 0, val maxLoad: Int = 2, var isAlive: Boolean = true) {
  fun get(): Provider {
    if (maxLoad == load) throw RuntimeException("Provider at max capacity")

    startJob()
    return this
  }

  fun check(): Boolean = maxLoad > load && isAlive

  private fun startJob() {
    load++
    GlobalScope.launch {
      delay(20000L)
      load--
    }
  }
}