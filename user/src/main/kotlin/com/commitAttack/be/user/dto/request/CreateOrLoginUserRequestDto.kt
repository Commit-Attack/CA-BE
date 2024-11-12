package com.commitAttack.be.user.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원가입 혹은 로그인 요청")
data class CreateOrLoginUserRequestDto(
    @Schema(description = "깃허브 아이디", example = "12345678", nullable = false)
    val githubId: String,
    @Schema(description = "깃허브 닉네임", example = "commitAttack", nullable = false)
    val name: String,
    @Schema(description = "깃허브 프로필 이미지 url", example = "https://avatars.githubusercontent.com/u/12345678?v=4", nullable = false)
    val profileImageUrl: String,
) {
}