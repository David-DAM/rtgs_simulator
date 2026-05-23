package com.davinchicoder.rtgs_simulator.payment.application

import com.davinchicoder.rtgs_simulator.payment.domain.PaymentRequest
import com.davinchicoder.rtgs_simulator.payment.domain.events.PaymentReceived
import com.davinchicoder.rtgs_simulator.payment.infrastructure.PaymentEntity
import com.davinchicoder.rtgs_simulator.payment.infrastructure.PaymentPublisher
import com.davinchicoder.rtgs_simulator.payment.infrastructure.PaymentQueryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PaymentService(
    private val paymentRepository: PaymentQueryRepository,
    private val paymentPublisher: PaymentPublisher
) {

    suspend fun createPayment(request: PaymentRequest) {

        val saved = paymentRepository.save(
            PaymentEntity(
                id = UUID.randomUUID().toString(),
                idempotencyKey = request.idempotencyKey.toString(),
                from = request.from.toString(),
                to = request.to.toString(),
                amount = request.amount,
                priority = request.priority
            )
        )

        paymentPublisher.publish(PaymentReceived(UUID.fromString(saved.id)))
    }

}