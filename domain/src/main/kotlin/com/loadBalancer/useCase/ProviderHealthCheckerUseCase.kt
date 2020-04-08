package com.loadBalancer.useCase

import com.loadBalancer.model.Provider
import com.loadBalancer.repository.ProviderRepository

class ProviderHealthCheckerUseCase(
    private val providerRepository: ProviderRepository,
    private val reviveProviderUseCase: ReviveProviderUseCase
) {
  private var validatedProviders = setOf<Provider>()


  fun execute() {
    val activeProviders = providerRepository.providers
        .filter { it.check() }

    activeProviders.filter {validatedProviders.contains(it)}.forEach {
      reviveProvider(it)
    }

    validatedProviders = setOf()
    activeProviders.forEach { validatedProviders.plus(it) }

  }

  private fun reviveProvider(provider: Provider) {
    reviveProviderUseCase.execute(provider.id!!)
    validatedProviders.minus(provider)
  }
}