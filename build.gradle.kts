import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
    id("org.jetbrains.kotlin.plugin.spring") version Versions.kotlin
    id("org.springframework.boot") version Versions.springBoot
}

apply(plugin = "io.spring.dependency-management")

apply(from = "gradle/generate-openapi.gradle.kts")

group = "by.cards"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    compileOnly("org.projectlombok:lombok")

    //TODO move to versions
    compileOnly("org.springframework.data:spring-data-commons:2.2.4.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-afterburner")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    //implementation("com.google.guava:guava:${Versions.guava}")
    //implementation("com.googlecode.libphonenumber:libphonenumber:${Versions.libphonenumber}")
    implementation("io.jsonwebtoken:jjwt-api:${Versions.jjwt}")
    implementation("io.swagger:swagger-annotations:${Versions.swaggerAnnotations}")
    implementation("org.apache.commons:commons-lang3")
    implementation("org.flywaydb:flyway-core:${Versions.flyway}")
    implementation("org.mapstruct:mapstruct-jdk8:${Versions.mapstruct}")
    //implementation("org.springframework.boot:spring-boot-starter-actuator")
    //implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    //implementation("org.springframework.boot:spring-boot-starter-mail")
    //implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.yaml:snakeyaml")
    //implementation("org.zalando:problem-spring-web:${Versions.problem}")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    runtimeOnly("io.jsonwebtoken:jjwt-impl:${Versions.jjwt}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Versions.jjwt}")
    runtimeOnly("javax.xml.bind:jaxb-api")
    runtimeOnly("org.postgresql:postgresql:${Versions.postgresql}")

    testImplementation("com.github.javafaker:javafaker:${Versions.javafaker}")
    testImplementation("org.awaitility:awaitility:${Versions.awaitility}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
    with(testLogging) {
        events = setOf(PASSED, SKIPPED, FAILED)
    }
}

kotlin.sourceSets["main"].kotlin.srcDirs("main")
sourceSets["main"].java.srcDirs("main")
sourceSets["main"].resources.srcDirs("resources")

kotlin.sourceSets["test"].kotlin.srcDirs("test")
sourceSets["test"].java.srcDirs("test")
