package com.commitAttack.be.user.controller

import com.commitAttack.be.user.api.AuthApi
import com.commitAttack.be.user.dto.response.GithubUserResponseDto
import com.commitAttack.be.user.service.GithubOAuthService
import com.commitAttack.web.dto.ApiSuccessResponseDto
import com.commitAttack.web.util.ResponseUtil
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "인증 관련 API", description = "인증 관련 API 입니다.")
class AuthController(
    private val githubOAuthService: GithubOAuthService
) : AuthApi {

    override fun getGithubUser(
        @RequestParam("accessToken") accessToken: String
    ): ResponseEntity<ApiSuccessResponseDto<GithubUserResponseDto>> {
        return ResponseUtil.successResponse("깃허브 유저 조회 성공", githubOAuthService.getUser(accessToken))
    }
}