package com.commitAttack.be.user.api

import com.commitAttack.be.user.dto.request.CreateOrLoginUserRequestDto
import com.commitAttack.be.user.dto.response.AccessTokenResponseDto
import com.commitAttack.be.user.dto.response.UserInfoResponseDto
import com.commitAttack.web.annotation.CAGetMapping
import com.commitAttack.web.annotation.CAPostMapping
import com.commitAttack.web.dto.ApiSuccessResponseDto
import com.commitAttack.web.exception.ErrorTitle
import com.commitAttack.web.exception.annotation.CustomFailResponseAnnotation
import com.commitAttack.web.security.service.CAUserDetails
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody

interface UserApi {

    @CAPostMapping("/users/login")
    @ApiResponse(responseCode = "200", description = "로그인 혹은 회원가입 성공")
    @Operation(summary = "로그인 혹은 회원가입을 하는 api 입니다.")
    @CustomFailResponseAnnotation(ErrorTitle.BadRequest)
    fun loginAndSignUp(
        @RequestBody @Valid request: CreateOrLoginUserRequestDto
    ) : ResponseEntity<ApiSuccessResponseDto<AccessTokenResponseDto>>


    @CAGetMapping("/users/info", authenticated = true)
    @ApiResponse(responseCode = "200", description = "유저의 정보 조회 성공")
    @Operation(summary = "유저의 정보를 조회하는 api 입니다.")
    @SecurityRequirement(name = "Bearer Authentication")
    @CustomFailResponseAnnotation(ErrorTitle.BadRequest)
    @CustomFailResponseAnnotation(ErrorTitle.BadRequest)
    fun getUserInfo(
        @AuthenticationPrincipal userDetails: CAUserDetails
    ) : ResponseEntity<ApiSuccessResponseDto<UserInfoResponseDto>>
}