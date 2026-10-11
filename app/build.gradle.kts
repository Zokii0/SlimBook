plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "uk.org.retallack.slimbook"
    compileSdk = 35

    defaultConfig {
        applicationId = "uk.org.retallack.slimbook"
        minSdk = 26
        targetSdk = 35
        versionCode = 3
        versionName = "1.2.0"
    }

    signingConfigs {
        create("release") {
            val keystoreFile = if (System.getenv("KEYSTORE_PATH") != null) {
                file(System.getenv("KEYSTORE_PATH"))
            } else if (System.getenv("SLIMBOOK_KEYSTORE_PATH") != null) {
                file(System.getenv("SLIMBOOK_KEYSTORE_PATH"))
            } else {
                file(System.getProperty("user.home") + "/.android/keystores/slimbook-release.jks")
            }
            storeFile = keystoreFile
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: System.getenv("SLIMBOOK_KEYSTORE_PASSWORD") ?: "slimbook-release"
            keyAlias = System.getenv("KEY_ALIAS") ?: "slimbook"
            keyPassword = System.getenv("KEY_PASSWORD") ?: System.getenv("SLIMBOOK_KEY_PASSWORD") ?: "slimbook-release"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
