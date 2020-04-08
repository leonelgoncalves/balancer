package com.loadBalancer.useCase

import com.loadBalancer.exception.NoAvailableProvidersException
import com.loadBalancer.model.Provider
import com.loadBalancer.repository.ProviderRepository

class GetAvailableProvidersUseCase(private val providerRepository: ProviderRepository) {

  fun execute(): List<Provider> =
      providerRepository.providers
          .filter { providerIsNotExcluded(it) }
          .filter { it.check() }
          .ifEmpty { throw NoAvailableProvidersException() }

  private fun providerIsNotExcluded(provider: Provider): Boolean {
    return !providerRepository.excludedProviders.contains(provider)
  }
}