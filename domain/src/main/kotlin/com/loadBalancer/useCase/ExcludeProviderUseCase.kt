package com.loadBalancer.useCase

import com.loadBalancer.model.Provider
import com.loadBalancer.repository.ProviderRepository

class ExcludeProviderUseCase(private val providerRepository: ProviderRepository) {

  fun execute(providerId: String) = providerRepository.excludeProvider(providerId)

}