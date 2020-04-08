package com.loadBalancer.useCase

import com.loadBalancer.repository.ProviderRepository

class ReviveProviderUseCase(private val providerRepository: ProviderRepository) {
  fun execute(providerId: String) {
    providerRepository.reviveProvider(providerId)
  }
}