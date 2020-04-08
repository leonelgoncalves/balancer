package com.loadBalancer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class DistributionApplication

fun main(args: Array<String>) {
  runApplication<DistributionApplication>(*args)
}
