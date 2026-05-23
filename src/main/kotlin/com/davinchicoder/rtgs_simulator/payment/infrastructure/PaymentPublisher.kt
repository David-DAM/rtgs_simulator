package com.davinchicoder.rtgs_simulator.payment.infrastructure

import com.davinchicoder.rtgs_simulator.common.DomainEvent
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

    fun publish(event: DomainEvent) {

        log.info("Publish domain event: $event")

        val serializedMessage = objectMapper.writeValueAsString(event)

        val buffer = UnsafeBuffer(ByteArray(1024))

        buffer.putStringUtf8(0, serializedMessage)

        publication.offer(buffer, 0, serializedMessage.length)

        log.info("Published domain event: $event")
    }
}