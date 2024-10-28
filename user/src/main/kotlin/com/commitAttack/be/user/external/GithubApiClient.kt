package com.commitAttack.be.user.external

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader


@FeignClient(
    name = "github-api",
    url = "https://api.github.com",
    configuration = [FeignConfig::class]
)

interface GithubApiClient {
    @GetMapping("/user")
    fun getUser(@RequestHeader("Authorization") authorization: String): GithubUserResponse
}

data class GithubUserResponse(
    val id: Long,
    val login: String,
    val name: String?,
    @JsonProperty("avatar_url")
    val avatarUrl: String?,
    val email: String?
)