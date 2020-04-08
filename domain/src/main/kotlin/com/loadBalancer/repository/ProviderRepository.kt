package com.loadBalancer.repository

import com.loadBalancer.model.Provider


interface ProviderRepository {
  val providers: List<Provider>
  val excludedProviders: List<Provider>

  fun registerProvider(provider: Provider): String

  fun excludeProvider(providerId: String)
  fun includeProvider(providerId: String)

  fun killProvider(providerId: String)
  fun reviveProvider(providerId: String)

  fun providerCount(): Int
}