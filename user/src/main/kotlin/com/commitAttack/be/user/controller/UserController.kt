package com.commitAttack.be.user.controller

import com.commitAttack.be.user.api.UserApi
import com.commitAttack.be.user.dto.request.CreateOrLoginUserRequestDto
import com.commitAttack.be.user.dto.response.AccessTokenResponseDto
import com.commitAttack.be.user.dto.response.UserInfoResponseDto
import com.commitAttack.be.user.service.UserService
import com.commitAttack.web.dto.ApiSuccessResponseDto
import com.commitAttack.web.security.service.CAUserDetails
import com.commitAttack.web.util.ResponseUtil
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController


@RestController
@Tag(name = "유저 관련 API", description = "유저 관련 API 입니다.")
class UserController(
    private val userService: UserService
) : UserApi {

    override fun loginAndSignUp(request: CreateOrLoginUserRequestDto): ResponseEntity<ApiSuccessResponseDto<AccessTokenResponseDto>> {
        return ResponseUtil.successResponse("로그인 혹은 회원가입 성공", userService.loginAndSignUp(request))
    }

    override fun getUserInfo(userDetails: CAUserDetails): ResponseEntity<ApiSuccessResponseDto<UserInfoResponseDto>> {
        return ResponseUtil.successResponse("유저의 정보 조회 성공", userService.getUserInfo(userDetails.getId()))
    }
}