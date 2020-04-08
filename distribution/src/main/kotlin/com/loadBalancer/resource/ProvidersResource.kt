package com.loadBalancer.resource

import com.loadBalancer.model.Provider
import com.loadBalancer.repository.ProviderRepository
import com.loadBalancer.useCase.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/providers")
class ProvidersResource(
    private val repository: ProviderRepository,

    private val getAvailableProvidersUseCase: GetAvailableProvidersUseCase,
    private val excludeProviderUseCase: ExcludeProviderUseCase,
    private val includeProviderUserCase: IncludeProviderUserCase,
    private val killProviderUseCase: KillProviderUseCase,
    private val reviveProviderUseCase: ReviveProviderUseCase,
    private val registerProviderUseCase: RegisterProviderUseCase

) {

  @GetMapping
  fun getAllProviders(): List<Provider> = repository.providers

  @GetMapping("/available")
  fun getAvailableProviders(): List<Provider> = getAvailableProvidersUseCase.execute()

  @PostMapping("/{providerId}/exclude")
  fun excludeProvider(@PathVariable providerId: String) {
    excludeProviderUseCase.execute(providerId)
  }

  @PostMapping("/{providerId}/revive")
  fun reviveProvider(@PathVariable providerId: String) {
    reviveProviderUseCase.execute(providerId)
  }

  @PostMapping("/{providerId}/kill")
  fun killProvider(@PathVariable providerId: String) {
    killProviderUseCase.execute(providerId)
  }

  @PostMapping("/{providerId}/include")
  fun includeProvider(@PathVariable providerId: String) {
    includeProviderUserCase.execute(providerId)
  }

  @PostMapping
  fun saveProvider(provider: Provider): String = this.registerProviderUseCase.execute(provider)

}