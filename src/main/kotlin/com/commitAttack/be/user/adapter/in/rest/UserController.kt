package com.commitAttack.be.user.adapter.`in`.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController : UserApi {
    override fun createUser() {
        TODO("Not yet implemented")
    }
}