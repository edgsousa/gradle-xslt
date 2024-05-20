# XSLT 1.0 Gradle Plugin

A simple plugin to run an XSLT transform

Requires Java 11 or newer.
## Usage

### Apply the plugin

```KTS
plugins {
    id("io.github.edgsousa.gradle.xslt").version("0.1")
}
```
### Create one or more tasks
The XSLT transform will be executed once for each file in models.
The output file name will be the same as the original model name, but changing the 
file extension to the provided suffix.
```KTS
tasks.register<XSLT>("taskname") {
    xsl = project.file("src/main/resources/stylesheet.xsl")
    models = project.fileTree("src/main/resources") {
        include("*.xml")
    }
    outputDirectory = project.buildDir.resolve("generated/taskname/")
    outputFileSuffix = "java"
}.also { task ->
    // if to run automatically the transform task
    tasks.withType<JavaCompile>().configureEach { dependsOn(task) }
}
```
### Include the generated sources 
If generating Java code e.g.
```KTS
sourceSets {
    main {
        java {
            srcDir(project.buildDir.resolve("generated/taskname/"))
        }
    }
}
```