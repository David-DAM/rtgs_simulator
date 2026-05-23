package com.davinchicoder.rtgs_simulator.bank

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BankQueryRepository : CoroutineCrudRepository<BankEntity, String> {
}