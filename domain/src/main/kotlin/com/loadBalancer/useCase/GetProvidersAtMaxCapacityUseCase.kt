package com.loadBalancer.useCase

import com.loadBalancer.repository.ProviderRepository

class GetProvidersAtMaxCapacityUseCase(private val providerRepository: ProviderRepository) {
  fun execute() = providerRepository.providers.filter { it.check() }
}