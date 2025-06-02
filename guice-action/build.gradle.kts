plugins {
    java
    kotlin("jvm") version "2.2.0-RC"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "${properties["project_group"]}"
version = "${properties["project_version"]}"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))

    implementation("com.google.inject:guice:7.0.0") {
        exclude("org.checkerframework", "checker-compat-qual")
        exclude("com.google.guava", "guava")
    }

    implementation("org.jetbrains.kotlin:kotlin-reflect:${properties["kotlin_version"]}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

intellij {
    version.set("${properties["idea_version"]}")
    type.set("IC")
    pluginName.set("${properties["plugin_name"]}")
    sandboxDir.set("idea-sandbox")
    plugins.set(listOf("java", "Kotlin"))
}