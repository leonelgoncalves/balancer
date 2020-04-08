package com.loadBalancer.useCase

import com.loadBalancer.model.Provider

class RoundRobinProviderInvokerUseCase(private val getAvailableProvidersUseCase: GetAvailableProvidersUseCase) {
  private var nextProvider: Int = 0

  @Throws(RuntimeException::class)
  fun execute(): Provider {
    val provider = getAvailableProvidersUseCase.execute()[nextProvider]

    nextProvider++
    nextProvider %= getAvailableProvidersUseCase.execute().count()
    return provider.get()
  }
}