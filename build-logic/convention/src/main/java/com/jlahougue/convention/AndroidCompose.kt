package com.jlahougue.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs
                .findVersion("composeCompiler")
                .get()
                .toString()
        }

        dependencies {
            val bom = libs.findLibrary("compose.bom").get()
            "implementation"(platform(bom))
            "androidTestImplementation"(platform(bom))
            "debugImplementation"(project.libs.findBundle("compose.debug").get())
            "debugImplementation"(libs.findLibrary("compose.tooling.preview").get())
            "androidTestImplementation"(project.libs.findLibrary("compose-junit4").get())
        }
    }
}