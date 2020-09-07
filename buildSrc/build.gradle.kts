plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("gradle-plugin-api"))
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
