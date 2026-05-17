package com.davinchicoder.rtgs_simulator.payment

import com.davinchicoder.rtgs_simulator.aeron.AeronMessage

data class PaymentRequest(
    val paymentId: String,
    val amount: Int
) : AeronMessage {
    init {
        require(paymentId.isNotBlank()) { "Payment ID cannot be blank" }
        require(amount > 0) { "Amount must be positive" }
    }
}
