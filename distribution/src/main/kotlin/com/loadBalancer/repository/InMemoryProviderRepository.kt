package com.loadBalancer.repository

import com.loadBalancer.model.Provider
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class InMemoryProviderRepository : ProviderRepository {

  override val providers = mutableListOf(
      Provider(id = UUID.randomUUID().toString()),
      Provider(id = UUID.randomUUID().toString()),
      Provider(id = UUID.randomUUID().toString())
  )
  override val excludedProviders = mutableListOf<Provider>()

  @Throws(RuntimeException::class)
  override fun registerProvider(provider: Provider): String {
    providers.add(provider)
    return provider.id!!
  }

  override fun excludeProvider(providerId: String) {
    providers.find { it.id == providerId }?.let { excludedProviders.add(it) }
  }

  override fun killProvider(providerId: String) {
    providers.find { it.id == providerId }?.let { it.isAlive = false }
  }

  override fun reviveProvider(providerId: String) {
    providers.find { it.id == providerId }?.let { it.isAlive = true }
  }

  override fun includeProvider(providerId: String) {
    providers.find { it.id == providerId }?.let { excludedProviders.remove(it) }
  }

  override fun providerCount(): Int = this.providers.size

}