package com.davinchicoder.rtgs_simulator.payment

import com.davinchicoder.rtgs_simulator.common.logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class PaymentController(
    private val publisher: PaymentPublisher,
) {

    companion object {
        private val log = logger<PaymentController>()
    }

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.ACCEPTED)
    suspend fun createPayment(@RequestBody request: PaymentRequest) {
        log.info("PAYMENT: $request")
        publisher.publish(request)
    }
}