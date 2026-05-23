package com.davinchicoder.rtgs_simulator.payment.infrastructure

import com.davinchicoder.rtgs_simulator.common.logger
import com.davinchicoder.rtgs_simulator.payment.application.PaymentService
import com.davinchicoder.rtgs_simulator.payment.domain.PaymentRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1")
@RestController
class PaymentController(
    private val service: PaymentService
) {

    companion object {
        private val log = logger<PaymentController>()
    }

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.ACCEPTED)
    suspend fun createPayment(@RequestBody request: PaymentRequest) {
        log.info("Received request: $request")
        service.createPayment(request)
    }
}