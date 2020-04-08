package com.loadBalancer.resource

import com.loadBalancer.exception.NoAvailableProvidersException
import com.loadBalancer.model.Provider
import com.loadBalancer.repository.ProviderRepository
import com.loadBalancer.useCase.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest

@RestController
@RequestMapping("api/invoker")
class InvokerResource(
    private val randomProviderInvoker: RandomProviderInvokerUseCase,
    private val roundRobinProviderInvoker: RoundRobinProviderInvokerUseCase
) {

  @GetMapping("/random-invoker")
  fun randomProviderInvoker(): Provider = randomProviderInvoker.execute()

  @GetMapping("/round-robin-invoker")
  fun roundRobinProviderInvoker(): Provider = roundRobinProviderInvoker.execute()

  @ExceptionHandler(value = [(NoAvailableProvidersException::class)])
  fun handleException(ex: NoAvailableProvidersException, request: WebRequest): ResponseEntity<String> {
    return ResponseEntity("No providers available", HttpStatus.TOO_MANY_REQUESTS)
  }

}