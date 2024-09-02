package com.commitAttack.be.user.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserEntityRepository : JpaRepository<UserEntity, UUID> {
}