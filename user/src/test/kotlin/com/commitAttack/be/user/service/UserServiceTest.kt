package com.commitAttack.be.user.service

import com.commitAttack.be.service.helper.CommonServiceTest
import com.commitAttack.be.user.domain.User
import com.commitAttack.be.user.domain.UserRepository
import com.commitAttack.be.user.dto.request.CreateOrLoginUserRequestDto
import com.commitAttack.be.user.external.GithubContributionApiClient
import com.commitAttack.web.jwt.service.JwtService
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    properties = [
        "jwt.secret=\${jwt.secret}",
    ]
)
class UserServiceTest : CommonServiceTest() {
    init {
        lateinit var userRepository: UserRepository
        lateinit var jwtService: JwtService
        lateinit var githubContributionApiClient: GithubContributionApiClient

        //Target
        lateinit var userService: UserService

        beforeEach {
            userRepository = mockk(relaxed = true)
            jwtService = mockk(relaxed = true)
            githubContributionApiClient = mockk()
            userService = UserService(userRepository, jwtService, githubContributionApiClient)
        }

        /**
            성공 케이스
         **/
        this.test("loginAndSignUp - 성공") {
            // Given
            val request = CreateOrLoginUserRequestDto(
                githubId = "testGithubId",
                name = "testName",
                profileImageUrl = "testProfileImageUrl"
            )
            val slotUser = slot<User>()

            every { userRepository.findByNameAndDeletedAtIsNull(request.name) } returns null

            //github Given
            every { githubContributionApiClient.fetchAllContributionYearsWithToken(request.name) } returns listOf(2021, 2022)
            every { githubContributionApiClient.fetchContributionCountWithToken(request.name, listOf(2021, 2022)) } returns 10

            every { userRepository.save(capture(slotUser)) } answers { slotUser.captured }
            // When
            val result = userService.loginAndSignUp(request)

            // Then
            result.accessToken shouldNotBe null

            slotUser.captured.githubId shouldBe request.githubId
            slotUser.captured.name shouldBe request.name
            slotUser.captured.profileImageUrl shouldBe request.profileImageUrl
            slotUser.captured.initialCommitCount shouldBe 10
        }
    }
}