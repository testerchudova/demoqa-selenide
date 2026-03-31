import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.artifacts.Configuration

plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val aspectJVersion = "1.9.21"

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("com.codeborne:selenide:7.4.2")

    testImplementation("io.qameta.allure:allure-junit5:2.24.0")
    testImplementation("io.qameta.allure:allure-selenide:2.24.0")

    agent("org.aspectj:aspectjweaver:$aspectJVersion")

    testImplementation("ch.qos.logback:logback-classic:1.5.6")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("com.codeborne:pdf-test:1.9.2")
    testImplementation("com.codeborne:xls-test:1.7.2")
    testImplementation("com.opencsv:opencsv:5.9")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    testImplementation("org.apache.poi:poi-ooxml:5.2.5")
}

tasks.test {
    useJUnitPlatform()
    systemProperties(System.getProperties().mapKeys { it.key.toString() })
    jvmArgs("-javaagent:${agent.singleFile}")

    systemProperty("allure.results.directory", "$buildDir/allure-results")

    val includeTagsProperty = System.getProperty("includeTags")
    val excludeTagsProperty = System.getProperty("excludeTags")

    if (!includeTagsProperty.isNullOrBlank()) {
        useJUnitPlatform {
            includeTags(*includeTagsProperty.split(",").map { it.trim() }.toTypedArray())
        }
    }

    if (!excludeTagsProperty.isNullOrBlank()) {
        useJUnitPlatform {
            excludeTags(*excludeTagsProperty.split(",").map { it.trim() }.toTypedArray())
        }
    }

}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}