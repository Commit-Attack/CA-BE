package com.commitAttack.be.user.controller

import com.commitAttack.be.user.api.UserApi
import com.commitAttack.be.user.service.UserService
import com.commitAttack.web.dto.ApiSuccessResponseDto
import com.commitAttack.web.util.ResponseUtil
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@Tag(name = "유저 관련 API", description = "유저 관련 API 입니다.")
class UserController(
    private val userService: UserService
) : UserApi {

    override fun signUpWithGithub(): ResponseEntity<ApiSuccessResponseDto<Unit>> {
        return ResponseUtil.successResponse("Github으로 회원가입에 성공했습니다.")
    }
}