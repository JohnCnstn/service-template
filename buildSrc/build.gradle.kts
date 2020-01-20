object Versions {

    // Do not forget to update buildSrc/main/Versions.kt
    const val awaitility = "3.1.6"            // https://mvnrepository.com/artifact/org.awaitility/awaitility
    const val docker = "3.1.2"                // https://mvnrepository.com/artifact/com.github.docker-java/docker-java
    const val flyway = "5.2.4"                // https://mvnrepository.com/artifact/org.flywaydb/flyway-core
    const val javaxActivation = "1.1.1"       // https://mvnrepository.com/artifact/javax.activation/activation
    const val jooq = "3.11.10"                // https://mvnrepository.com/artifact/org.jooq/jooq
    const val openapiGenerator = "3.3.4"      // https://mvnrepository.com/artifact/org.openapitools/openapi-generator
    const val postgresql = "42.2.5"           // https://mvnrepository.com/artifact/org.postgresql/postgresql

}

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.docker-java:docker-java:${Versions.docker}")
    implementation("javax.activation:activation:${Versions.javaxActivation}")
    implementation("org.awaitility:awaitility:${Versions.awaitility}")
    implementation("org.flywaydb:flyway-core:${Versions.flyway}")
    implementation("org.jooq:jooq-codegen:${Versions.jooq}")
    implementation("org.jooq:jooq-meta:${Versions.jooq}")
    implementation("org.jooq:jooq:${Versions.jooq}")
    implementation("org.openapitools:openapi-generator:${Versions.openapiGenerator}")
    implementation("org.postgresql:postgresql:${Versions.postgresql}")
}

kotlin.sourceSets["main"].kotlin.srcDirs("main")
sourceSets["main"].java.srcDirs("main")
sourceSets["main"].resources.srcDirs("resources")
