plugins {
    application
}

application {
    mainClass.set("com.github.gilday.cdk.MyServerlessApplication")
}

val assets = configurations.register("assets") {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
    implementation("software.amazon.awscdk:aws-cdk-lib:2.67.0")
    add(assets.name, project(":my-custom-lambda-runtime", configuration = "lambda"))
}

val copyAssets by tasks.registering(Copy::class) {
    from(assets)
    into(project.layout.buildDirectory.dir("assets"))
}

tasks.assemble {
    dependsOn(copyAssets)
}

tasks.named("run", JavaExec::class) {
    workingDir = file("..")
}
