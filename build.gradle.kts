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
    val xslt by plugins.creating {
        id = "io.github.edgsousa.gradle.xslt"
        implementationClass = "io.github.edgsousa.gradle.xslt.XsltPlugin"
    }
}