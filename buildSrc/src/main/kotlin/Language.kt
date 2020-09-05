import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.get

enum class Language {
    Java {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer,
            configurations: ConfigurationContainer) {
            sourceSets.register(folder) {
                java.srcDir("solution/$folder")
                compileClasspath += configurations["javaCompileDependencies"]
                runtimeClasspath += configurations["javaRuntimeDependencies"]
                sourceSets["test"].runtimeClasspath += this.output.classesDirs
            }
        }
    },
    Kotlin {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer,
            configurations: ConfigurationContainer) {
            sourceSets.register(folder) {
                java.srcDir("solution/$folder")
                compileClasspath += configurations["javaCompileDependencies"]
                runtimeClasspath += configurations["javaRuntimeDependencies"]
                compileClasspath += configurations["kotlinCompileDependencies"]
                runtimeClasspath += configurations["kotlinRuntimeDependencies"]
                sourceSets["test"].runtimeClasspath += this.output.classesDirs
            }
        }
    },
    Python {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer,
            configurations: ConfigurationContainer) {
        }
    };

    abstract fun register(
        folder: LanguageDir, sourceSets: SourceSetContainer,
        configurations: ConfigurationContainer)
}
