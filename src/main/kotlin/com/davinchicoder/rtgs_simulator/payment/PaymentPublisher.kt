package com.davinchicoder.rtgs_simulator.payment

import com.davinchicoder.rtgs_simulator.aeron.AeronMessage
import com.davinchicoder.rtgs_simulator.common.logger
import io.aeron.ConcurrentPublication
import org.agrona.concurrent.UnsafeBuffer
import org.springframework.stereotype.Service
import tools.jackson.databind.ObjectMapper

@Service
class PaymentPublisher(
    private val publication: ConcurrentPublication,
    private val objectMapper: ObjectMapper
) {

    companion object {
        private val log = logger<PaymentPublisher>()
    }

    fun publish(message: AeronMessage) {

        log.info("PUBLISH: $message")

        val serializedMessage = objectMapper.writeValueAsString(message)

        val buffer = UnsafeBuffer(ByteArray(1024))

        buffer.putStringUtf8(0, serializedMessage)

        publication.offer(buffer, 0, serializedMessage.length)

        log.info("PUBLISHED: $message")
    }
}