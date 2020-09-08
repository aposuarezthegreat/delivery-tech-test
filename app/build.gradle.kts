import deps

plugins {
    id("base-android-application")
}

dependencies {

    implementation(deps.kotlin.stdlib)

    implementation(deps.androidx.app_compat)
    implementation(deps.androidx.ktx.core_ktx)
    implementation(deps.androidx.ktx.fragment_ktx)
    implementation(deps.androidx.design)
    implementation(deps.androidx.constraint_layout)

    implementation(deps.androidx.navigation.fragment_ktx)
    implementation(deps.androidx.navigation.ui_ktx)

    implementation(deps.airbnb.epoxy)
    kapt(deps.airbnb.epoxy_processor)

    implementation(deps.uniflow.runtime)
    testImplementation(deps.uniflow.testing)

    implementation(deps.okhttp3.okhttp)
    implementation(deps.okhttp3.interceptor)
    implementation(deps.retrofit2.retrofit)
    implementation(deps.retrofit2.rxjava2_adapter)
    implementation(deps.retrofit2.gson_converter)
    implementation(deps.rxjava2.rxjava)
    implementation(deps.rxjava2.rxandroid)
    implementation(deps.gson)

    kapt(deps.room.compiler)
    implementation(deps.room.runtime)
    implementation(deps.room.room_ktx)

    implementation(deps.koin.android)
    implementation(deps.koin.androidx.viewmodel)

    testImplementation(deps.testing.junit)
    androidTestImplementation(deps.androidx.testing.junit)
    androidTestImplementation(deps.androidx.testing.espresso)
}