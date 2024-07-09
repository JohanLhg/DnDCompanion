import com.jlahougue.convention.configureKotlinJvm
import com.jlahougue.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("org.jetbrains.kotlin.jvm")

            configureKotlinJvm()

            dependencies {
                "implementation"(libs.findLibrary("kotlin.coroutines").get())
                "testImplementation"(libs.findLibrary("junit").get())
                "testImplementation"(libs.findLibrary("test-coroutines").get())
            }
        }
    }
}