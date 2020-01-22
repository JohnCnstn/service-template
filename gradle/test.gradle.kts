dependencies {
    "testImplementation"("com.github.javafaker:javafaker:${Versions.javafaker}")
    "testImplementation"("org.awaitility:awaitility:${Versions.awaitility}")
    "testImplementation"("org.springframework.boot:spring-boot-starter-tests") {
        exclude("org.junit.vintage:junit-vintage-engine")
    }
    //TODO move to versions
    "testImplementation"("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    "testImplementation"("org.springframework.security:spring-security-tests")
    "testImplementation"("org.testcontainers:testcontainers:${Versions.testcontainers}")
}
