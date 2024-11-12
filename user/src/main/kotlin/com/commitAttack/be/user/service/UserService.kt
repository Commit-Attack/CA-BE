package com.commitAttack.be.user.service

import com.commitAttack.be.user.domain.User
import com.commitAttack.be.user.domain.UserRepository
import com.commitAttack.be.user.dto.request.CreateOrLoginUserRequestDto
import com.commitAttack.be.user.dto.response.AccessTokenResponseDto
import com.commitAttack.be.user.dto.response.UserInfoResponseDto
import com.commitAttack.be.user.jwt.createJwt
import com.commitAttack.web.exception.ApiException
import com.commitAttack.web.exception.ErrorTitle
import com.commitAttack.web.jwt.service.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    @Value("\${jwt.common.key}")
    private val jwtService: JwtService
) {

    @Transactional
    fun loginAndSignUp(request: CreateOrLoginUserRequestDto) : AccessTokenResponseDto{
        val user = userRepository.findByGithubIdAndDeletedAtIsNull(request.githubId) ?: userRepository.save(
            User(
                githubId = request.githubId,
                name = request.name,
                profileImageUrl = request.profileImageUrl,
            )
        )
        return AccessTokenResponseDto(jwtService.createJwt(user.id, user.githubId, user.profileImageUrl, user.name))
    }

    @Transactional(readOnly = true)
    fun getUserInfo(userId: String): UserInfoResponseDto {
        val findUser = userRepository.findByIdAndDeletedAtIsNull(userId) ?: throw ApiException(ErrorTitle.BadRequest) // 수정 예정

        return UserInfoResponseDto(
            id = findUser.id,
            githubId = findUser.githubId,
            name = findUser.name,
            profileImageUrl = findUser.profileImageUrl
        )
    }
}
