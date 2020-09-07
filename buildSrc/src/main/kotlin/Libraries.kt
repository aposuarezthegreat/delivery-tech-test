@file:Suppress("ClassName")

object deps {

    object kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${versions.kotlin}"
        const val gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
    }

    object androidx {
        const val app_compat = "androidx.appcompat:appcompat:${versions.app_compat}"

        const val design = "com.google.android.material:material:${versions.material_design}-beta01"
        const val constraint_layout = "androidx.constraintlayout:constraintlayout:${versions.constraint_layout}"

        object ktx {
            const val core_ktx = "androidx.core:core-ktx:${versions.core_ktx}"
            const val fragment_ktx = "androidx.fragment:fragment-ktx:${versions.fragment_ktx}"
        }

        object navigation {
            const val fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${versions.navigation}"
            const val ui_ktx = "androidx.navigation:navigation-ui-ktx:${versions.navigation}"
            const val safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:${versions.navigation}"
        }

        object testing {
            const val junit = "androidx.test.ext:junit:${versions.junit_ext}"
            const val espresso = "androidx.test.espresso:espresso-core:${versions.espresso_core}"
        }
    }

    object koin {
        const val core = "org.koin:koin-core:${versions.koin}"
        const val android = "org.koin:koin-android:${versions.koin}"

        object androidx {
            const val viewmodel = "org.koin:koin-androidx-viewmodel:${versions.koin}"
        }
    }

    object uniflow {
        const val runtime = "io.uniflow:uniflow-androidx:${versions.uniflow}"
        const val testing = "io.uniflow:uniflow-androidx-test:${versions.uniflow}"
    }

    object okhttp3 {
        const val okhttp = "com.squareup.okhttp3:okhttp:${versions.okhttp3}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${versions.okhttp3}"
    }

    object retrofit2 {
        const val retrofit = "com.squareup.retrofit2:retrofit:${versions.retrofit2}"
        const val rxjava2_adapter = "com.squareup.retrofit2:adapter-rxjava2:${versions.retrofit2}"
        const val gson_converter = "com.squareup.retrofit2:converter-gson:${versions.retrofit2}"
    }

    object rxjava2 {
        const val rxjava = "io.reactivex.rxjava2:rxjava:${versions.rxjava}"
        const val rxandroid = "io.reactivex.rxjava2:rxandroid:${versions.rxandroid}"
    }

    object testing {
        const val junit = "junit:junit:${versions.junit}"
    }

    const val gson = "com.google.code.gson:gson:${versions.gson}"

}
