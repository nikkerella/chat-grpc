# Chat-gRPC-Android

``` cmd
protoc --proto_path=app/src/main/proto --java_out=app/src/main/java \
       --grpc-java_out=app/src/main/java --plugin=protoc-gen-grpc-java=/opt/homebrew/bin/protoc-gen-grpc-java \
       --grpc-kotlin_out=app/src/main/java --plugin=protoc-gen-grpc-kotlin=/opt/homebrew/bin/protoc-gen-grpc-kotlin \
       chat.proto
       
./gradlew clean
./gradlew generateProto --refresh-dependencies       

ls app/src/main/java/chat/
```