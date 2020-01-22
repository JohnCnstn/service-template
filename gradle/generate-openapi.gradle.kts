val generateOpenapi by tasks.creating(GenerateOpenapiTask::class) {
    inputFile = project.rootDir.path + "/src/main/resources/openapi/openapi.yaml"
    outputDirectory = project.rootDir.path + "/src/main/java"
    apiPackage = "com.servicetemplate.generated.api"
    modelPackage = "com.servicetemplate.generated.model"
}

tasks {
    "clean" {
        doFirst {
            delete(project.rootDir.path + "/src/main/java/com/servicetemplate/generated")
        }
    }
    "compileJava" { dependsOn(generateOpenapi) }
    "compileKotlin" { dependsOn(generateOpenapi) }
}
