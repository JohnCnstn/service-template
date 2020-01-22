import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
    id("org.jetbrains.kotlin.plugin.spring") version Versions.kotlin
    id("org.springframework.boot") version Versions.springBoot
    checkstyle
    jacoco
    pmd
}

apply(plugin = "io.spring.dependency-management")

apply(from = "gradle/generate-openapi.gradle.kts")
apply(from = "gradle/checkstyle.gradle")
apply(from = "gradle/jacoco.gradle")

group = "com.servicetemplate"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    compileOnly("org.projectlombok:lombok")
    compileOnly("org.springframework.data:spring-data-commons:2.2.4.RELEASE")

    //TODO move to versions
    implementation("io.swagger:swagger-annotations:1.5.21")
    implementation("com.fasterxml.jackson.module:jackson-module-afterburner")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.jsonwebtoken:jjwt-api:${Versions.jjwt}")
    implementation("io.swagger:swagger-annotations:${Versions.swaggerAnnotations}")
    implementation("org.apache.commons:commons-lang3")
    implementation("org.flywaydb:flyway-core:${Versions.flyway}")
    implementation("org.mapstruct:mapstruct-jdk8:${Versions.mapstruct}")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.yaml:snakeyaml")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    runtimeOnly("io.springfox:springfox-swagger-ui:2.9.2") {
        exclude("springfox-spring-web")
    }
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${Versions.jjwt}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Versions.jjwt}")
    runtimeOnly("javax.xml.bind:jaxb-api")
    runtimeOnly("org.postgresql:postgresql:${Versions.postgresql}")

    testImplementation("com.github.javafaker:javafaker:${Versions.javafaker}")
    testImplementation("org.awaitility:awaitility:${Versions.awaitility}")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage:junit-vintage-engine")
    }

    //TODO move to versions
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:testcontainers:${Versions.testcontainers}")
}

configurations {
    compileOnly {
        extendsFrom(annotationProcessor.get())
    }
    implementation {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.bootRun {
    args = listOf("--spring.profiles.active=local")
    jvmArgs = listOf("-server", "-Xms256m", "-Xmx512m", "-Duser.country=US", "-Duser.language=en", "-Duser.timezone=UTC")
}

tasks.test {
    useJUnitPlatform()
    with(testLogging) {
        events = setOf(PASSED, SKIPPED, FAILED)
    }
}
