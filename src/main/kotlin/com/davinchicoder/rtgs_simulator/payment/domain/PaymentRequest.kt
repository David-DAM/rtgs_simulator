package com.davinchicoder.rtgs_simulator.payment.domain

import com.davinchicoder.rtgs_simulator.common.DomainEvent
import java.math.BigDecimal
import java.util.*

data class PaymentRequest(
    val id: UUID,
    val idempotencyKey: UUID,
    val from: UUID,
    val to: UUID,
    val amount: BigDecimal,
    val status: PaymentStatus = PaymentStatus.RECEIVED,
    val priority: PaymentPriority = PaymentPriority.NORMAL
) : DomainEvent {
    init {
        require(id != UUID(0, 0)) { "Payment ID cannot be zero" }
        require(idempotencyKey != UUID(0, 0)) { "Idempotency key cannot be zero" }
        require(from != UUID(0, 0)) { "From account ID cannot be zero" }
        require(to != UUID(0, 0)) { "To account ID cannot be zero" }
        require(from != to) { "From and To account IDs cannot be the same" }
        require(amount > BigDecimal.ZERO) { "Amount must be positive" }
    }
}