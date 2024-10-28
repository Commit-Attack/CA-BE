package com.commitAttack.be.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableJpaAuditing
@EnableFeignClients
class UserApplication

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}