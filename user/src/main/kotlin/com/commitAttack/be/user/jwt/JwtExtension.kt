package com.commitAttack.be.user.jwt

import com.commitAttack.web.jwt.service.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts

fun JwtService.createJwt(userId: String, githubId: String, profileImageUrl: String?, name: String): String {
    val claims: Claims = Jwts.claims()
        .setSubject(userId)
        .apply {
            put("githubId", githubId)
            put("profileImageUrl", profileImageUrl)
            put("name", name)
        }
    val jwtDto = generateToken(null, claims)
    return jwtDto.accessToken
}
