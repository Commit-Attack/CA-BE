package com.commitAttack.be.service.helper

import com.commitAttack.be.user.domain.User

class CommonServiceHelper {

    val testUser = createUser()

    private fun createUser(): User {
        return User(
            githubId = "testGithubId",
            name = "testName",
            profileImageUrl = "testProfileImageUrl",
            initialCommitCount = 100
        )
    }
}