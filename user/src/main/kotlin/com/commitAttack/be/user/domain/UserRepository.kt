package com.commitAttack.be.user.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
}