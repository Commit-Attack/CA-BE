package com.commitAttack.be.user.service

import com.commitAttack.be.user.domain.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun signUpWithGithub() {
        // TODO
        // Github 로그인을 통한 회원가입 로직 구현
    }
}