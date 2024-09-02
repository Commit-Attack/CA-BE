package com.commitAttack.be.user.domain

import java.time.OffsetDateTime
import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val profileImageUrl: String,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
    val deletedAt: OffsetDateTime?
) {
}