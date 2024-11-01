package com.commitAttack.be.user.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 정보 응답")
data class UserInfoResponseDto(
    @Schema(description = "유저 id", example = "12ASDWIN12312DSADAS", nullable = false)
    val id: String,
    @Schema(description = "깃허브 아이디", example = "12345678", nullable = false)
    val githubId: String,
    @Schema(description = "깃허브 닉네임", example = "commitAttack", nullable = false)
    val name: String,
    @Schema(description = "깃허브 프로필 이미지 url", example = "https://avatars.githubusercontent.com/u/12345678?v=4", nullable = true)
    val profileImageUrl: String?,
)
