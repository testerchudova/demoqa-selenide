plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.codeborne:selenide:7.4.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // ✅ Логи (SLF4J + Logback)
    testImplementation("ch.qos.logback:logback-classic:1.5.6")

    implementation ("com.github.javafaker:javafaker:1.0.2")

    testImplementation ("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation ("com.codeborne:selenide:7.2.3")
    testImplementation ("com.codeborne:pdf-test:1.9.2")
    testImplementation ("com.codeborne:xls-test:1.7.2")
    testImplementation ("com.opencsv:opencsv:5.9")
    testImplementation ("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    testImplementation("org.apache.poi:poi-ooxml:5.2.5")
}

tasks.test {
    useJUnitPlatform {
        val includeTagsProperty = System.getProperty("includeTags")
        val excludeTagsProperty = System.getProperty("excludeTags")

        if (!includeTagsProperty.isNullOrBlank()) {
            includeTags(*includeTagsProperty.split(",").map { it.trim() }.toTypedArray())
        }

        if (!excludeTagsProperty.isNullOrBlank()) {
            excludeTags(*excludeTagsProperty.split(",").map { it.trim() }.toTypedArray())
        }
    }
}