package com.commitAttack.be.user.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

        @GetMapping("/healthz")
        fun healthCheck(): ResponseEntity<String> {
            return ResponseEntity.ok("ok")
        }
}