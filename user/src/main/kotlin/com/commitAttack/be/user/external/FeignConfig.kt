package com.commitAttack.be.user.external

import com.commitAttack.web.exception.ApiException
import com.commitAttack.web.exception.ErrorTitle
import feign.Logger
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {
    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun errorDecoder(): ErrorDecoder {
        return ErrorDecoder { _, response ->
            when (response.status()) {
                400 -> throw ApiException(ErrorTitle.BadRequest)
                401 -> throw ApiException(ErrorTitle.Unauthorized)
                403 -> throw ApiException(ErrorTitle.Forbidden)
                else -> throw ApiException(ErrorTitle.ExternalServerError)
            }
        }
    }
}