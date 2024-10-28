package com.commitAttack.be.user.dto.request

data class CreateUserRequestDto(
    val githubId: String,
    val name: String,
    val profileImageUrl: String,
) {
}