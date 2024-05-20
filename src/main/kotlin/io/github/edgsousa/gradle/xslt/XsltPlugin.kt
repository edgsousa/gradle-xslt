package io.github.edgsousa.gradle.xslt

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.work.ChangeType
import org.gradle.work.Incremental
import org.gradle.work.InputChanges
import java.io.File

class XsltPlugin : Plugin<Project> {
    override fun apply(target: Project) { /* nothing */ }
}

open class XSLT: DefaultTask() {
    @get:InputFile
    lateinit var xsl: File

    @get:InputFiles
    @get:Incremental
    lateinit var models: FileCollection

    @get:OutputDirectory
    lateinit var outputDirectory: File

    @Internal
    lateinit var outputFileSuffix: String

    @TaskAction
    fun execute(inputChanges: InputChanges) {

        inputChanges.getFileChanges(models)
            .filter { !it.file.isDirectory }
            .filter { it.changeType != ChangeType.REMOVED }
            .forEach { fc ->
                val name = "${fc.file.name.substringBeforeLast('.')}.$outputFileSuffix"
                val args = arrayOf(
                    "-XSL", xsl.absolutePath,
                    "-IN", fc.file.absolutePath,
                    "-OUT", File(outputDirectory, name).absolutePath
                )
                org.apache.xalan.xslt.Process.main(args)
            }
    }
}