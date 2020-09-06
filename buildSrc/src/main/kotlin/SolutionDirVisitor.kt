import org.gradle.api.file.FileVisitDetails
import org.gradle.api.file.FileVisitor
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class SolutionDirVisitor : FileVisitor {

    val languageDirs: Map<LanguageDir, Set<Language>>
        get() = languageDir

    private val logger: Logger = Logging.getLogger(SolutionDirVisitor::class.java)

    private val languageDir: MutableMap<LanguageDir, MutableSet<Language>> = mutableMapOf()

    private var visitingDir: String? = null

    private val languageExtensions = mapOf(
        ".java" to Language.Java,
        ".kt" to Language.Kotlin,
        ".py" to Language.Python,
        ".mysql.sql" to Language.MySql,
        ".bash.sh" to Language.Bash
    )

    override fun visitDir(dirDetails: FileVisitDetails) {
        logger.info("Visiting dir {}", dirDetails.name)
        val isSolutionDir =
            dirDetails.isDirectory && dirDetails.name.startsWith("#")
        visitingDir = dirDetails.path.takeIf { isSolutionDir }
    }

    override fun visitFile(fileDetails: FileVisitDetails) {
        logger.info("Visiting file {}", fileDetails.name)
        val visitingDir = visitingDir ?: return
        if (!fileDetails.path.startsWith(visitingDir)) {
            // path prefix is not match, which means
            // we are in (or back to) the root folder.
            this.visitingDir = null
            return
        }

        languageExtensions
            .filterKeys { fileDetails.name.endsWith(it) }
            .values.forEach {
                languageDir
                    .computeIfAbsent(visitingDir) { mutableSetOf() }
                    .add(it)
            }
    }
}
