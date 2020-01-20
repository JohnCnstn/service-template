val generateOpenapi by tasks.creating(GenerateOpenapiTask::class) {
    inputFile = project.rootDir.path + "/src/main/resources/openapi/openapi.yaml"
    outputDirectory = project.rootDir.path + "/src/main/java"
    apiPackage = "com.letsdev.employeedevelopmentcards.generated.api"
    modelPackage = "com.letsdev.employeedevelopmentcards.generated.model"
}

tasks {
    "clean" {
        doFirst {
            delete(project.rootDir.path + "/src/main/java/com/letsdev/employeedevelopmentcards/generated")
        }
    }
    "compileJava" { dependsOn(generateOpenapi) }
    "compileKotlin" { dependsOn(generateOpenapi) }
}
