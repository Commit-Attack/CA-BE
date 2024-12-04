package com.commitAttack.be.user.external

import com.commitAttack.be.user.external.dto.ContributionCountResponse
import com.commitAttack.be.user.external.dto.ContributionYearsResponse
import com.commitAttack.be.user.external.dto.GithubGraphQLResponse
import com.commitAttack.web.exception.ApiException
import com.commitAttack.web.exception.ErrorTitle
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.nio.charset.Charset
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

@Component
class GithubContributionApiClient(
    @Value("\${github.token}") private val token: String,
) {
    companion object {
        private const val NAME_PLACEHOLDER = "*{name}"
        private const val YEAR_PLACEHOLDER = "*{year}"
    }

    private val restClient = RestClient.create("https://api.github.com/graphql")
    private val executors = Executors.newFixedThreadPool(32)
    private val queryLoader = GraphQLQueryLoader()

    fun fetchContributionCountWithToken(
        username: String,
        years: List<Int>
    ): Int {
        return fetchContributionCountForYear(username, years)
    }

    private inline fun <reified T : GithubGraphQLResponse, R> executeGraphQLQuery(
        query: String,
        extractResult: (T) -> R
    ): R {
        return try {
            val response = restClient.post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer $token")
                .body(mapOf("query" to query))
                .retrieve()
                .body(T::class.java)
                ?: throw ApiException(ErrorTitle.ExternalServerError, "not found response body")

            extractResult(response)
        } catch (e: Exception) {
            throw ApiException(ErrorTitle.ExternalServerError, "failed to fetch contribution count")
        }
    }

    private fun fetchContributionCountForYear(username: String, years: List<Int>): Int {
        val completableFutures = mutableListOf<CompletableFuture<Int>>()
        years.forEach { year ->
            val completableFuture = CompletableFuture.supplyAsync({
                val query = queryLoader.loadContributionCountByYearQuery()
                    .replace(NAME_PLACEHOLDER, username)
                    .replace(YEAR_PLACEHOLDER, year.toString())

                executeGraphQLQuery<ContributionCountResponse, Int>(query) { response ->
                    response.data.user.contributionsCollection.contributionCalendar.totalContributions
                }
            }, executors)
            completableFutures.add(completableFuture)
        }
        return completableFutures.sumOf { it.get() }
    }

    fun fetchAllContributionYearsWithToken(username: String): List<Int> {
        val query = queryLoader.loadContributionYearsQuery()
            .replace(NAME_PLACEHOLDER, username)

        return executeGraphQLQuery<ContributionYearsResponse, List<Int>>(query) { response ->
            response.data.user.contributionsCollection.contributionYears
        }
    }

    // GraphQL 쿼리 로더
    private class GraphQLQueryLoader {
        fun loadContributionYearsQuery(): String =
            ClassPathResource("graphql/contribution-year.graphql")
                .getContentAsString(Charset.defaultCharset())

        fun loadContributionCountByYearQuery(): String =
            ClassPathResource("graphql/contribution-count-by-year.graphql")
                .getContentAsString(Charset.defaultCharset())
    }
}