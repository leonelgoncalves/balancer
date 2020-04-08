package com.loadBalancer.useCase


import com.loadBalancer.model.Provider
import com.loadBalancer.repository.ProviderRepository

class RandomProviderInvokerUseCase(private val getAvailableProvidersUseCase: GetAvailableProvidersUseCase) {

  @Throws(RuntimeException::class)
  fun execute(): Provider {
    val provider = getAvailableProvidersUseCase.execute().random()
    provider.get()
    return provider
  }

}