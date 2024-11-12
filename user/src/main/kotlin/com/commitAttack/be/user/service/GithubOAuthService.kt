package com.commitAttack.be.user.service

import com.commitAttack.be.user.dto.response.GithubTokenResponseDto
import com.commitAttack.be.user.dto.response.GithubUserResponseDto
import com.commitAttack.be.user.external.GithubApiClient
import com.commitAttack.be.user.external.GithubTokenApiClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GithubOAuthService(
    private val githubTokenApiClient: GithubTokenApiClient,
    private val githubApiClient: GithubApiClient,
    @Value("\${github.client-id}")
    private val clientId: String,
    @Value("\${github.client-secret}")
    private val clientSecret: String,
) {
    fun getAccessToken(code: String): GithubTokenResponseDto {
        val response = githubTokenApiClient.getAccessToken(
            clientId = clientId,
            clientSecret = clientSecret,
            code = code
        )
        return GithubTokenResponseDto(response.accessToken)
    }

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