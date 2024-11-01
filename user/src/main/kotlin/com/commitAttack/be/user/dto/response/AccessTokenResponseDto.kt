package com.commitAttack.be.user.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "액세스 토큰 응답")
data class AccessTokenResponseDto(
    @Schema(description = "액세스 토큰", example = "eyJhbGci...", nullable = false)
    val accessToken: String
)
