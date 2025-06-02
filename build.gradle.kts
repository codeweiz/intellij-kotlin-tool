import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    java
    kotlin("jvm") version "2.2.0-RC"
    application
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "maven-publish")
    apply(plugin = "java-library")
    apply(plugin = "signing")

    tasks.withType<JavaCompile> {
        sourceCompatibility = "${properties["jdk_version"]}"
        targetCompatibility = "${properties["jdk_version"]}"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget("${properties["jdk_version"]}"))
            apiVersion.set(KotlinVersion.fromVersion("${properties["language_version"]}"))
            languageVersion.set(KotlinVersion.fromVersion("${properties["language_version"]}"))
        }
    }
}

group = "${properties["project_group"]}"
version = "${properties["project_version"]}"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(properties["jdk_version"].toString().toInt()))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    // Google
    implementation("com.google.code.gson:gson:2.13.1")
    implementation("com.google.inject:guice:7.0.0")

    // Commons
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("commons-io:commons-io:2.19.0")

    // Test
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:${properties["junit_version"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${properties["junit_version"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${properties["junit_version"]}")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(properties["jdk_version"].toString().toInt())
}