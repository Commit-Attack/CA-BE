package com.commitAttack.be.user.dto.response

data class GithubUserResponseDto(
    val id: Long,
    val login: String,
    val name: String?,
    val avatarUrl: String?,
    val email: String?
) {
}