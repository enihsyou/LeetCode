import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.get

enum class Language {
    Java {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer,
            configurations: ConfigurationContainer) {
            sourceSets.maybeCreate(folder).run {
                java.setSrcDirs(listOf("solution/$folder"))
                compileClasspath += configurations["javaCompileDependencies"]
                compileClasspath += sourceSets["main"].output
                runtimeClasspath += configurations["javaRuntimeDependencies"]
                runtimeClasspath += sourceSets["main"].output
                sourceSets["test"].runtimeClasspath += this.output.classesDirs
            }
        }
    },
    Kotlin {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer,
            configurations: ConfigurationContainer) {
            sourceSets.maybeCreate(folder).run {
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
            sourceSets.maybeCreate(folder).run {
                if (java.srcDirs.none { it.parentFile?.name == "solution" }) {
                    java.setSrcDirs(emptyList<String>())
                }
                resources.srcDir("solution/$folder")
            }
        }
    },

    MySql {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer,
            configurations: ConfigurationContainer) {
            sourceSets.maybeCreate(folder).run {
                if (java.srcDirs.none { it.parentFile?.name == "solution" }) {
                    java.setSrcDirs(emptyList<String>())
                }
                resources.srcDir("solution/$folder")
            }
        }
    },

    Bash {

        override fun register(
            folder: LanguageDir, sourceSets: SourceSetContainer,
            configurations: ConfigurationContainer) {
            sourceSets.maybeCreate(folder).run {
                if (java.srcDirs.none { it.parentFile?.name == "solution" }) {
                    java.setSrcDirs(emptyList<String>())
                }
                resources.srcDir("solution/$folder")
            }
        }
    };

    abstract fun register(
        folder: LanguageDir, sourceSets: SourceSetContainer,
        configurations: ConfigurationContainer)
}
