package com.davinchicoder.rtgs_simulator.aeron

import io.aeron.Aeron
import io.aeron.ConcurrentPublication
import io.aeron.Subscription
import io.aeron.driver.MediaDriver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AeronConfig {

    @Bean
    fun mediaDriver(): MediaDriver {
        return MediaDriver.launchEmbedded()
    }

    @Bean
    fun aeron(mediaDriver: MediaDriver): Aeron {
        return Aeron.connect(
            Aeron.Context()
                .aeronDirectoryName(mediaDriver.aeronDirectoryName())
        )
    }

    @Bean
    fun concurrentPublication(aeron: Aeron, properties: AeronProperties): ConcurrentPublication {
        return aeron.addPublication(properties.channel, properties.streamId)
    }

    @Bean
    fun subscription(aeron: Aeron, properties: AeronProperties): Subscription {
        return aeron.addSubscription(properties.channel, properties.streamId)
    }
}