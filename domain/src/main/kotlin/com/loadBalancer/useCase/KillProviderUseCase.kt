package com.loadBalancer.useCase

import com.loadBalancer.repository.ProviderRepository

class KillProviderUseCase(private val providerRepository: ProviderRepository) {
  fun execute(providerId: String) {
    providerRepository.killProvider(providerId)
  }
}