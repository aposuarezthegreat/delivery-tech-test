package plugins

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions


class AndroidApplication : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("com.android.application")
        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-android-extensions")
        project.plugins.apply("kotlin-kapt")

        project.androidApp {
            compileSdkVersion(app_versions.compileSdk)

            defaultConfig {
                applicationId = app_versions.applicationId

                minSdkVersion(app_versions.minSdk)
                targetSdkVersion(app_versions.targetSdk)

                versionCode = app_versions.versionCode
                versionName = app_versions.versionName

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                multiDexEnabled = true

                ndk {
                    abiFilters("armeabi-v7a", "arm64-v8a")
                }
            }

            buildTypes {
                getByName("debug") {
                    isMinifyEnabled = false
                    isShrinkResources = false
                }
                getByName("release") {
//                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            compileOptions {
                targetCompatibility = JavaVersion.VERSION_1_8
                sourceCompatibility = JavaVersion.VERSION_1_8
            }

            kotlinOptions {
                jvmTarget = "1.8"
            }

            addKotlinSourceSets()
        }

    }

    val Project.androidApp: BaseAppModuleExtension
        get() = extensions.findByType(BaseAppModuleExtension::class.java)
            ?: error("Android module is not an app module. Can't access 'android' app extension.")

    val Project.kotlinOptions: KotlinJvmOptions
        get() = (this as ExtensionAware).extensions.findByType(KotlinJvmOptions::class.java)
            ?: error("'kotlinOptions' extension does not exist.")

    fun BaseExtension.kotlinOptions(configure: KotlinJvmOptions.() -> Unit) {
        (this as ExtensionAware).extensions.configure(KotlinJvmOptions::class.java, configure)
    }

    fun BaseExtension.addKotlinSourceSets() {

        sourceSets {
            named("main") {
                java.srcDirs("src/main/kotlin")
            }
        }
    }
    fun Project.androidApp(configure: BaseAppModuleExtension.() -> Unit) {
        extensions.configure(BaseAppModuleExtension::class.java, configure)
    }
}