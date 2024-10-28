package com.commitAttack.be.user.service

import com.commitAttack.be.user.domain.User
import com.commitAttack.be.user.domain.UserRepository
import com.commitAttack.be.user.dto.request.CreateUserRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional
    fun createMember(request: CreateUserRequestDto): User {
        return userRepository.save(
            User(
                githubId = request.githubId,
                name = request.name,
                profileImageUrl = request.profileImageUrl,
            )
        )
    }
}