package com.commitAttack.be.user.api

import com.commitAttack.web.annotation.CAPostMapping
import com.commitAttack.web.dto.ApiSuccessResponseDto
import com.commitAttack.web.exception.ErrorTitle
import com.commitAttack.web.exception.annotation.CustomFailResponseAnnotation
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity

interface UserApi {

    @CAPostMapping("/signup")
    @ApiResponse(responseCode = "200", description = "회원가입에 성공했습니다.")
    @Operation(summary = "깃허브 로그인을 하는 API 입니다.")
    @CustomFailResponseAnnotation(ErrorTitle.BadRequest)
    fun signUpWithGithub() : ResponseEntity<ApiSuccessResponseDto<Unit>>
}