package com.davinchicoder.rtgs_simulator.payment.domain.events

import com.davinchicoder.rtgs_simulator.common.DomainEvent
import java.util.*

data class PaymentSettled(
    val paymentId: UUID
) : DomainEvent
