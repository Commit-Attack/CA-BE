package com.commitAttack.be.user.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "깃허브 토큰 응답 DTO")
data class GithubTokenResponseDto(
    @Schema(description = "액세스 토큰", example = "gho_16C5t7eZqKyjBZjNnT8bY7Zz0Xv8ZqT4TlFb")
    val accessToken: String
)
