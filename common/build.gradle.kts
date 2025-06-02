
group = "${properties["project_group"]}"
version = "${properties["project_version"]}"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${properties["kotlin_version"]}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${properties["kotlin_version"]}")

    // Google
    implementation("com.google.code.gson:gson:2.13.1")
    implementation("com.google.inject:guice:7.0.0")

    // commons
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("commons-io:commons-io:2.19.0")

    // test
    testImplementation("org.jetbrains.kotlin:kotlin-test:${properties["kotlin_version"]}")
    testImplementation("org.jetbrains.kotlin:kotlin-test-common:${properties["kotlin_version"]}")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:${properties["kotlin_version"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${properties["junit_version"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${properties["junit_version"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${properties["junit_version"]}")
    testImplementation("org.apache.groovy:groovy-jsr223:5.0.0-beta-1")
    testImplementation("org.mockito:mockito-core:5.18.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
