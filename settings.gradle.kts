rootProject.name = "cdk-issue-24827"

pluginManagement {
    repositories.gradlePluginPortal()
}

dependencyResolutionManagement {
    repositories.mavenCentral()
}

include(":my-custom-lambda-runtime", "my-serverless-application")
