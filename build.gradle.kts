plugins {
    java
    kotlin("jvm") version "1.3.61"
}

sourceSets {
    main {
        resources {
            srcDir("src/main/shell")
        }
    }
    test {
        java {
            srcDir("src/main/java")
            srcDir("src/main/kotlin")
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    runtimeOnly("org.junit.platform:junit-platform-launcher:1.6.0")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    implementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    implementation("org.assertj:assertj-core:3.15.0")

    runtimeOnly(platform("org.apache.logging.log4j:log4j-bom:2.13.0"))
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl")
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
