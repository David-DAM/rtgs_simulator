package com.davinchicoder.rtgs_simulator.payment.domain

enum class PaymentStatus {
    RECEIVED,
    VALIDATED,
    QUEUED,
    RESERVED,
    SETTLED,
    REJECTED,
    FAILED
}