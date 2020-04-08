package com.loadBalancer.useCase

class ExcludeProvidersAtMaxCapacityUseCase(
    private val getProvidersAtMaxCapacityUseCase: GetProvidersAtMaxCapacityUseCase,
    private val excludeProviderUseCase: ExcludeProviderUseCase
) {
  fun execute() {
    getProvidersAtMaxCapacityUseCase.execute().forEach {
      excludeProviderUseCase.execute(it.id!!)
    }
  }

}