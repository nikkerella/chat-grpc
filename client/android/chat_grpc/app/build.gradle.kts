import com.google.protobuf.gradle.id

buildscript {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.protobuf")
}

android {
    namespace = "nikkerella.chat_grpc"
    compileSdk = 35

    defaultConfig {
        applicationId = "nikkerella.chat_grpc"
        minSdk = 28
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

//val grpcVersion = "1.51.0"
//val grpcKotlinVersion = "1.3.0"
//val protobufVersion = "3.21.12"
//val coroutinesVersion = "1.6.4"

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.grpc.okhttp)
    implementation(libs.grpc.kotlin.stub)
    implementation(libs.grpc.protobuf)
    implementation(libs.protobuf.java.util)
    implementation(libs.javax.annotation.api)
}

//protobuf {
//    protoc {
//        // The artifact spec for the Protobuf Compiler
//        artifact = "com.google.protobuf:protoc:3.24.2"
//    }
//    plugins {
//        // Optional: an artifact spec for a protoc plugin, with "grpc" as
//        // the identifier, which can be referred to in the "plugins"
//        // container of the "generateProtoTasks" closure.
//        id("grpc") {
//            artifact = "io.grpc:protoc-gen-grpc-java:1.57.2"
//        }
//    }
//    // omitted protoc and plugins config
//    generateProtoTasks {
//        all().forEach {
//            // omitted plugins config
//            it.builtins {
//                id("kotlin")
//            }
//            it.plugins {
//                // Apply the "grpc" plugin whose spec is defined above, without
//                // options. Note the braces cannot be omitted, otherwise the
//                // plugin will not be added. This is because of the implicit way
//                // NamedDomainObjectContainer binds the methods.
//                id("grpc") { }
//            }
//        }
//    }
//}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.2"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.57.2"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc")
            }
        }
    }
}