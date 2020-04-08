package com.loadBalancer.useCase

import com.loadBalancer.model.Provider
import com.loadBalancer.repository.ProviderRepository
import java.lang.RuntimeException
import java.util.*

class RegisterProviderUseCase(private val providerRepository: ProviderRepository ) {

  @Throws(RuntimeException::class)
   fun execute(provider: Provider): String {
    if (this.providerRepository.providerCount() == 10) {
      throw RuntimeException("Max Capacity reached")
    }
    return this.providerRepository.registerProvider(provider.copy(id = UUID.randomUUID().toString()));
  }

}