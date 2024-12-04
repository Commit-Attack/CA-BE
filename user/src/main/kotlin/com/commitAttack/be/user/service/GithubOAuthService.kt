package com.commitAttack.be.user.service

import com.commitAttack.be.user.dto.response.GithubUserResponseDto
import com.commitAttack.be.user.external.GithubApiClient
import org.springframework.stereotype.Service

@Service
class GithubOAuthService(
    private val githubApiClient: GithubApiClient,
) {

    fun getUser(accessToken: String): GithubUserResponseDto {
        val response =  githubApiClient.getUser("Bearer $accessToken")
        return GithubUserResponseDto(
            id = response.id,
            login = response.login,
            name = response.name,
            avatarUrl = response.avatarUrl,
            email = response.email
        )
    }

}