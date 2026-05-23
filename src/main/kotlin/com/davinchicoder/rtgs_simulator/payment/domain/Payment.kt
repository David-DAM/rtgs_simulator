package com.davinchicoder.rtgs_simulator.payment.domain

import java.math.BigDecimal
import java.util.*

data class Payment(
    val id: UUID,
    val idempotencyKey: UUID,
    val from: UUID,
    val to: UUID,
    val amount: BigDecimal,
    var status: PaymentStatus,
    val priority: PaymentPriority
)