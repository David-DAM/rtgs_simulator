package com.davinchicoder.rtgs_simulator.bank

import java.math.BigDecimal

data class BankAccount(
    val bankId: String,
    val availableLiquidity: BigDecimal,
    val reservedLiquidity: BigDecimal
)
