import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
	repositories {
		mavenCentral()
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
		maven {
			url = uri("https://maven.pkg.github.com/Commit-Attack/CA-Be-Library")
			credentials {
				username = project.findProperty("GITHUB_USER") as String? ?: System.getenv("GITHUB_USER")
				password = project.findProperty("GITHUB_TOKEN") as String? ?: System.getenv("GITHUB_TOKEN")
			}
		}
	}
}

subprojects {
	apply {
		plugin("kotlin")
		plugin("org.jetbrains.kotlin.jvm")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
		plugin("org.jetbrains.kotlin.plugin.allopen")
		plugin("org.jetbrains.kotlin.plugin.noarg")
		plugin("org.jetbrains.kotlin.plugin.spring")
		apply(plugin = "kotlin-kapt")
	}
	dependencyManagement {
		imports {
			mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
			mavenBom("io.awspring.cloud:spring-cloud-aws:3.1.0")
		}
	}

	// Test Dependencies(Note: test does not allow api type dependencies)
	dependencies {
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.springframework.boot:spring-boot-testcontainers")
		testImplementation("org.springframework.security:spring-security-test")
		testImplementation("org.testcontainers:junit-jupiter")
		testImplementation("org.testcontainers:postgresql")
		testImplementation("org.testcontainers:localstack")
		testImplementation("org.testcontainers:elasticsearch")
		testImplementation("io.kotest:kotest-assertions-core:5.8.0")
		testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
		testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
		testImplementation("io.kotest.extensions:kotest-extensions-testcontainers:2.0.2")
		testImplementation("io.mockk:mockk:1.13.10")
		testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner:4.1.1")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}