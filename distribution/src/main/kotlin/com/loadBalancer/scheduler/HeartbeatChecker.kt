package com.loadBalancer.scheduler

import com.loadBalancer.useCase.ExcludeProvidersAtMaxCapacityUseCase
import com.loadBalancer.useCase.GetAvailableProvidersUseCase
import com.loadBalancer.useCase.ProviderHealthCheckerUseCase
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class HeartbeatChecker(
    private val getAvailableProvidersUseCase: GetAvailableProvidersUseCase,
    private val providerHealthCheckerUseCase: ProviderHealthCheckerUseCase
) {

  private val logger = LoggerFactory.getLogger(HeartbeatChecker::class.java)

  /**
   * This @Schedule annotation run every 5 seconds in this case. It can also
   * take a cron like syntax.
   * See https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
   */
  @Scheduled(fixedRate = 5000)
  fun reportTime() {
    logger.info("-------------------------------")
    providerHealthCareOrchestrator()
    getAvailableProvidersUseCase.execute().forEach {
      logger.info("${it.id!!.subSequence(0, 4)} ${it.load}/${it.maxLoad}")
    }
  }

  private fun providerHealthCareOrchestrator() {
    // excludeProvidersAtMaxCapacityUseCase.execute()
    providerHealthCheckerUseCase.execute()
  }
}