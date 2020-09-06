import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.get

enum class Language {
    Java {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer,
            configurations: ConfigurationContainer) {
            sourceSets.register(folder) {
                java.setSrcDirs(listOf("solution/$folder"))
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
                java.setSrcDirs(listOf("solution/$folder"))
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
            sourceSets.register(folder) {
                java.setSrcDirs(emptyList<String>())
                resources.srcDir("solution/$folder")
            }
        }
    },

    MySql {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer, configurations: ConfigurationContainer) {
            sourceSets.register(folder) {
                java.setSrcDirs(emptyList<String>())
                resources.srcDir("solution/$folder")
            }
        }
    },

    Bash {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer, configurations: ConfigurationContainer) {
            sourceSets.register(folder) {
                java.setSrcDirs(emptyList<String>())
                resources.srcDir("solution/$folder")
            }
        }
    };

    abstract fun register(
        folder: LanguageDir, sourceSets: SourceSetContainer,
        configurations: ConfigurationContainer)
}
