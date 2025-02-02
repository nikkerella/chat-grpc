import chat.ChatMessage
import chat.ChatServiceGrpcKt
import chat.ReceiveRequest
import chat.SendResponse
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class GrpcChatClient(private val host: String, private val port: Int) {
    private val channel: ManagedChannel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext() // Use plaintext for testing; use TLS in production
        .build()

    private val stub = ChatServiceGrpcKt.ChatServiceCoroutineStub(channel)

    suspend fun sendMessage(fromId: String, toId: String, text: String): Boolean {
        return withContext(Dispatchers.IO) {
            val request = ChatMessage.newBuilder()
                .setFromId(fromId)
                .setToId(toId)
                .setText(text)
                .setTimestamp(System.currentTimeMillis())
                .build()

            try {
                val response: SendResponse = stub.sendMessage(request)
                response.success
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    suspend fun receiveMessages(clientId: String) {
        withContext(Dispatchers.IO) {
            val request = ReceiveRequest.newBuilder().setClientId(clientId).build()
            try {
                stub.receiveMessages(request).collect { message ->
                    println("Received: ${message.fromId} -> ${message.text}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun shutdown() {
        channel.shutdown()
    }
}