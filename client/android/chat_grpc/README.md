# Chat-gRPC

## Install gRPC and Kotlin Plugins for protoc

1. Install ``protoc-gen-grpc-kotlin``

Install via Gradle in the build.gradle (project level).
``` cmd
buildscript {
    dependencies {
        classpath(libs.google.protobuf.gradle.plugin)
    }
}
```