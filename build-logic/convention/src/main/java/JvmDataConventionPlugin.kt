import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class JvmDataConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("dndcompanion.jvm.room")

            dependencies {
                "implementation"(project(":core:core-data-interface"))
            }
        }
    }
}