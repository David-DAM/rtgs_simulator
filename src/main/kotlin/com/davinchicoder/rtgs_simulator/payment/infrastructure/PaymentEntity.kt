package com.davinchicoder.rtgs_simulator.payment.infrastructure

import com.davinchicoder.rtgs_simulator.payment.domain.PaymentPriority
import com.davinchicoder.rtgs_simulator.payment.domain.PaymentStatus
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("payments")
class PaymentEntity(
    @Id
    var id: String,
    var idempotencyKey: String,
    var from: String,
    var to: String,
    var amount: BigDecimal,
    var status: PaymentStatus = PaymentStatus.RECEIVED,
    var priority: PaymentPriority = PaymentPriority.NORMAL
)