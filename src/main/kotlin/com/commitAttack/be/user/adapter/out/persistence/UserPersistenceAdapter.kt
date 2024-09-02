package com.commitAttack.be.user.adapter.out.persistence

import com.commitAttack.be.user.application.port.out.CreateUserPort

class UserPersistenceAdapter(
    private val userEntityRepository: UserEntityRepository
) : CreateUserPort {
    override fun createUser() {
        println("UserPersistenceAdapter.createUser")
    }
}