package com.davinchicoder.rtgs_simulator.payment.domain

import java.time.Instant
import java.util.*

data class ProcessedMessage(
    val messageId: UUID,
    val processedAt: Instant
)
