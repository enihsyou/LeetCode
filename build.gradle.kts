import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.72"
}

repositories {
    mavenCentral()
}

/** Dependencies like implementation() but for solutions use java */
val javaCompileDependencies: Configuration by configurations.creating

/** Dependencies like runtimeOnly() but for solutions use java */
val javaRuntimeDependencies: Configuration by configurations.creating

/** Dependencies like implementation() but for solutions use kotlin */
val kotlinCompileDependencies: Configuration by configurations.creating

/** Dependencies like runtimeOnly() but for solutions use kotlin */
val kotlinRuntimeDependencies: Configuration by configurations.creating

dependencies {

    javaCompileDependencies("org.assertj:assertj-core:3.15.0")
    javaCompileDependencies("org.junit.jupiter:junit-jupiter-api:5.6.0")
    javaCompileDependencies("org.junit.jupiter:junit-jupiter-params:5.6.0")
    javaCompileDependencies("org.junit.jupiter:junit-jupiter-engine:5.6.0")

    javaRuntimeDependencies("org.apache.logging.log4j:log4j-slf4j-impl:2.13.0")

    kotlinCompileDependencies(kotlin("stdlib"))
}

val solutionDirVisitor: SolutionDirVisitor = SolutionDirVisitor()
fileTree("solution").visit(solutionDirVisitor)

solutionDirVisitor.languageDirs.forEach { (folder, implementLanguages) ->
    for (language in implementLanguages) {
        language.register(folder, project.sourceSets, project.configurations)
    }
}

sourceSets {
    main {
        compileClasspath += javaCompileDependencies
    }
}

//configurations {
//    compileClasspath.get().extendsFrom(javaCompileDependencies)
//}

tasks {
    test {
        useJUnitPlatform()
        failFast = false

        // include compiled solution class file into test task.
        solutionDirVisitor.languageDirs.keys.forEach { folder ->
            classpath += sourceSets.getByName(folder).output.classesDirs
            testClassesDirs += sourceSets.getByName(folder).output.classesDirs
        }
        // set classpath for 'test' task, then we can invoke the test task
        // bundled with Gradle.
        // instead doing in `configurations.testRuntimeOnly`, we are assigning
        // values here to reduce dependencies leaks into default configurations
        classpath += javaCompileDependencies
        classpath += javaRuntimeDependencies
        classpath += kotlinCompileDependencies
        classpath += kotlinRuntimeDependencies
    }

    withType<KotlinCompile>().configureEach {
        // test targeting JVM options in one line.
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    }
}
