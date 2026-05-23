package com.davinchicoder.rtgs_simulator.bank

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("bank")
data class BankEntity(

    @Id
    val id: String,
    val availableLiquidity: BigDecimal,
    val reservedLiquidity: BigDecimal
)
