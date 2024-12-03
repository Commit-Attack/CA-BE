package com.commitAttack.be.user.external.dto

sealed interface GithubGraphQLResponse {
    val data: Any
}

data class ContributionYearsResponse(
    override val data: Data
) : GithubGraphQLResponse {
    data class Data(val user: User) {
        data class User(val contributionsCollection: ContributionsCollection) {
            data class ContributionsCollection(val contributionYears: List<Int>)
        }
    }
}

data class ContributionCountResponse(
    override val data: Data
) : GithubGraphQLResponse {
    data class Data(val user: User) {
        data class User(val contributionsCollection: ContributionsCollection) {
            data class ContributionsCollection(
                val contributionCalendar: ContributionCalendar
            ) {
                data class ContributionCalendar(val totalContributions: Int)
            }
        }
    }
}
