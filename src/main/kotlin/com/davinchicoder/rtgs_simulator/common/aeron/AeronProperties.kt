package com.davinchicoder.rtgs_simulator.common.aeron

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "app.aeron")
class AeronProperties {

    lateinit var channel: String
    var streamId: Int = 0
    var pollFragmentLimit: Int = 0
    var pollInterval: Long = 0

}