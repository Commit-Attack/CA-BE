package com.commitAttack.be.user.api

import com.commitAttack.be.user.dto.response.GithubUserResponseDto
import com.commitAttack.web.annotation.CAGetMapping
import com.commitAttack.web.dto.ApiSuccessResponseDto
import com.commitAttack.web.exception.ErrorTitle
import com.commitAttack.web.exception.annotation.CustomFailResponseAnnotation
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam

interface AuthApi {

    @CAGetMapping("/auth/github/users")
    @ApiResponse(responseCode = "200", description = "깃허브 유저 조회 성공")
    @Operation(summary = "깃허브 유저 프로필 조회하는 api 입니다.")
    @CustomFailResponseAnnotation(ErrorTitle.BadRequest)
    @CustomFailResponseAnnotation(ErrorTitle.Forbidden)
    @CustomFailResponseAnnotation(ErrorTitle.ExternalServerError)
    fun getGithubUser(
        @RequestParam("accessToken") accessToken: String
    ) : ResponseEntity<ApiSuccessResponseDto<GithubUserResponseDto>>
}