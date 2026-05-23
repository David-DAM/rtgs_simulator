package com.davinchicoder.rtgs_simulator.payment.infrastructure

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PaymentQueryRepository : CoroutineCrudRepository<PaymentEntity, Long> {
}