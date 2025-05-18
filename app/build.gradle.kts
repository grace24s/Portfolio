import java.security.MessageDigest
tasks.register("delcomChecking") {
    dependsOn("lintReportDebug")
    doLast {
        val f = File(layout.projectDirectory.toString(), "build/reports/lint-results-debug.html")
        if (f.exists()) {
            val c = f.readText()
            val r = """<span class="mdl-layout-title">([^<]+)</span>""".toRegex()
            val m = r.find(c)
            val w = m?.groupValues?.get(1) ?: "Lint warnings not found"
            println(w)

            val md5 = f.inputStream().use { inputStream ->
                val md = MessageDigest.getInstance("MD5")
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    md.update(buffer, 0, bytesRead)
                }
                val hashBytes = md.digest()
                hashBytes.joinToString("") { "%02x".format(it) }
            }
            println("MD5 Hash: $md5")
        } else {
            println("Lint report file not found: ${f.absolutePath}")
        }
    }
}
tasks.named("lint") {
    finalizedBy("delcomChecking")
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}


android {
    namespace = "del.ifs22022.portfolio"
    compileSdk = 35

    defaultConfig {
        applicationId = "del.ifs22022.portfolio"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

}

dependencies {
    // Core Android dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    // Compose dependencies
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")

    // Material Icons Extended
    implementation("androidx.compose.material:material-icons-extended")

    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Window size classes
    implementation("androidx.window:window:1.2.0")

    // Coil for image loading (optional)
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation(libs.androidx.uiautomator)

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Debug dependencies
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}