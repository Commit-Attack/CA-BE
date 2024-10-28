package com.commitAttack.be.user.external

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "github-token",
    url = "https://github.com",
    configuration = [FeignConfig::class]
)
interface GithubTokenApiClient {
    @PostMapping(
        value = ["/login/oauth/access_token"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        headers = ["Accept=application/json"]
    )
    fun getAccessToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("code") code: String
    ): GithubTokenResponse
}

data class GithubTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("scope")
    val scope: String
)
