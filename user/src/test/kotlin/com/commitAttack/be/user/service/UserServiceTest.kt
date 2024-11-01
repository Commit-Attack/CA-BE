package com.commitAttack.be.user.service

import com.commitAttack.be.service.helper.CommonServiceTest
import com.commitAttack.be.user.domain.User
import com.commitAttack.be.user.domain.UserRepository
import com.commitAttack.be.user.dto.request.CreateOrLoginUserRequestDto
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

        //Target
        lateinit var userService: UserService

        beforeEach {
            userRepository = mockk()
            jwtService = mockk(relaxed = true)
            userService = UserService(userRepository, jwtService)
        }

        this.test("loginAndSignUp - 성공") {
            // Given
            val request = CreateOrLoginUserRequestDto(
                githubId = "testGithubId",
                name = "testName",
                profileImageUrl = "testProfileImageUrl"
            )
            val slotUser = slot<User>()

            every { userRepository.findByGithubIdAndDeletedAtIsNull(request.githubId) } returns null
            every { userRepository.save(capture(slotUser)) } answers { slotUser.captured }

            // When
            val result = userService.loginAndSignUp(request)

            // Then
            result.accessToken shouldNotBe null

            slotUser.captured.githubId shouldBe request.githubId
            slotUser.captured.name shouldBe request.name
            slotUser.captured.profileImageUrl shouldBe request.profileImageUrl
        }
    }
}