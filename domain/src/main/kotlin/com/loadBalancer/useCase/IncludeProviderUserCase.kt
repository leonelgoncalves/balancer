package com.loadBalancer.useCase

import com.loadBalancer.repository.ProviderRepository

class IncludeProviderUserCase(private val providerRepository: ProviderRepository) {

  fun execute(providerId: String) {
    providerRepository.includeProvider(providerId)
  }
}