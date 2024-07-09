import com.jlahougue.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class JvmRoomConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("dndcompanion.jvm.library")

            dependencies {
                "implementation"(libs.findLibrary("room.common").get())
                "implementation"(project(":core:core-domain"))
            }
        }
    }
}