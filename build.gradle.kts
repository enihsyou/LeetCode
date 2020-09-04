plugins {
    java
    kotlin("jvm") version "1.3.61"
}

repositories {
    mavenCentral()
}

val javaCompileDependencies: Configuration by configurations.creating
val javaRuntimeDependencies: Configuration by configurations.creating
val kotlinDependencies: Configuration by configurations.creating

dependencies {

    javaCompileDependencies("org.assertj:assertj-core:3.15.0")
    javaCompileDependencies("org.junit.jupiter:junit-jupiter-api:5.6.0")
    javaCompileDependencies("org.junit.jupiter:junit-jupiter-params:5.6.0")

    javaRuntimeDependencies("org.junit.platform:junit-platform-launcher:1.6.0")
    javaRuntimeDependencies("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    javaRuntimeDependencies("org.apache.logging.log4j:log4j-slf4j-impl:2.13.0")

    kotlinDependencies(kotlin("stdlib-jdk8"))
    kotlinDependencies("io.kotlintest:kotlintest-runner-junit5:3.4.2")
}


sourceSets {
    register("#22 Generate Parentheses") {
        java {
            srcDir("solution/#22 Generate Parentheses")
            compileClasspath += javaCompileDependencies
            runtimeClasspath += javaRuntimeDependencies
        }
    }
    register("#39 Combination Sum") {
        java {
            srcDir("solution/#39 Combination Sum")
            compileClasspath += javaCompileDependencies
            runtimeClasspath += javaRuntimeDependencies
        }
    }
    main {
        resources {
            srcDir("src/main/bash")
            srcDir("src/main/mysql")
        }
    }
    test {
        java {
            srcDir("src/main/java")
            srcDir("src/main/kotlin")
        }
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    }
}
