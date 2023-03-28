plugins {
    java
    id("com.google.cloud.tools.jib") version "3.3.1"
}

jib {
    from {
        image = "gcr.io/distroless/java11-debian11"
    }
    to {
        image = project.name
    }
    container {
        mainClass = "com.amazonaws.services.lambda.runtime.api.client.AWSLambda"
    }
    outputPaths {
        tar = "${project.buildDir.name}/distributions/${project.name}-image.tar"
    }
}

dependencies {
    implementation("com.amazonaws:aws-lambda-java-events:3.10.0")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
    runtimeOnly("com.amazonaws:aws-lambda-java-runtime-interface-client:2.2.0")
}

val lambda by configurations.registering {
    isCanBeConsumed = true
    isCanBeResolved = false
}

artifacts {
    add(lambda.name, tasks.jibBuildTar)
}
