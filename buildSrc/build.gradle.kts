plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(kotlin("gradle-plugin-api"))
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
