plugins {
    kotlin("jvm") version "1.9.23"
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "io.github.edgsousa.gradle"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("xalan:xalan:2.7.3")
    runtimeOnly("xalan:serializer:2.7.3")
}

kotlin {
    jvmToolchain(11)
}

gradlePlugin {
    website.set("https://github.com/edgsousa/gradle-xslt/")
    vcsUrl.set("https://github.com/edgsousa/gradle-xslt.git")
    val xslt by plugins.creating {
        id = "io.github.edgsousa.gradle.xslt"
        implementationClass = "io.github.edgsousa.gradle.xslt.XsltPlugin"
        displayName = "XSLT Gradle Plugin"
        description = "Run XSLT 1.0 from your Gradle build file"
        tags.set(listOf("xslt", "xsd", "xalan", "xerces", "transform", "xalan-java", "xsl"))
    }
}