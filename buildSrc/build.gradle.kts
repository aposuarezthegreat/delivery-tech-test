plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

gradlePlugin {
    plugins {
        create("AndroidApplication") {
            id = "base-android-application"
            implementationClass = "plugins.AndroidApplication"
        }
    }
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.61")
    implementation("com.android.tools.build:gradle:3.5.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
}