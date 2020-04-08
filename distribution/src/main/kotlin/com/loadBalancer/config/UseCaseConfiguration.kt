package com.loadBalancer.config

import com.loadBalancer.repository.ProviderRepository
import com.loadBalancer.useCase.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {

  @Bean
  fun registerProviderUserCase(providerRepository: ProviderRepository): RegisterProviderUseCase =
      RegisterProviderUseCase(providerRepository)

  @Bean
  fun getAvailableProvidersUseCase(providerRepository: ProviderRepository): GetAvailableProvidersUseCase =
      GetAvailableProvidersUseCase(providerRepository)

  @Bean
  fun excludeProviderUseCase(providerRepository: ProviderRepository): ExcludeProviderUseCase =
      ExcludeProviderUseCase(providerRepository)

  @Bean
  fun includeProviderUseCase(providerRepository: ProviderRepository): IncludeProviderUserCase =
      IncludeProviderUserCase(providerRepository)
  @Bean
  fun killProviderUseCase(providerRepository: ProviderRepository): KillProviderUseCase =
      KillProviderUseCase(providerRepository)

  @Bean
  fun reviveProviderUseCase(providerRepository: ProviderRepository): ReviveProviderUseCase =
      ReviveProviderUseCase(providerRepository)

  @Bean
  fun providerHealthCheckerUseCase(providerRepository: ProviderRepository, reviveProviderUseCase: ReviveProviderUseCase): ProviderHealthCheckerUseCase =
      ProviderHealthCheckerUseCase(providerRepository, reviveProviderUseCase)

  @Bean
  fun excludeProvidersAtMaxCapacityUseCase(getProvidersAtMaxCapacityUseCase: GetProvidersAtMaxCapacityUseCase,
                                           excludeProviderUseCase: ExcludeProviderUseCase
  ): ExcludeProvidersAtMaxCapacityUseCase =
      ExcludeProvidersAtMaxCapacityUseCase(getProvidersAtMaxCapacityUseCase, excludeProviderUseCase)

  @Bean
  fun getProvidersAtMaxCapacity(providerRepository: ProviderRepository): GetProvidersAtMaxCapacityUseCase =
      GetProvidersAtMaxCapacityUseCase(providerRepository)

  @Bean
  fun randomProviderInvokerUseCase(useCase: GetAvailableProvidersUseCase): RandomProviderInvokerUseCase =
      RandomProviderInvokerUseCase(useCase)

  @Bean
  fun roundRobinProviderInvokerUseCase(useCase: GetAvailableProvidersUseCase): RoundRobinProviderInvokerUseCase =
      RoundRobinProviderInvokerUseCase(useCase)

}
