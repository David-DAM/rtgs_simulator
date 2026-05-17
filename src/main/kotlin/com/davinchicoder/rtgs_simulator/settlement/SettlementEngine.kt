package com.davinchicoder.rtgs_simulator.settlement

import com.davinchicoder.rtgs_simulator.aeron.AeronProperties
import com.davinchicoder.rtgs_simulator.common.logger
import io.aeron.Subscription
import io.aeron.logbuffer.FragmentHandler
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import kotlinx.coroutines.*
import org.agrona.concurrent.BackoffIdleStrategy
import org.springframework.stereotype.Component
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

@Component
class SettlementEngine(
    private val properties: AeronProperties,
    private val subscription: Subscription
) {

    companion object {
        private val log = logger<SettlementEngine>()
    }

    private val running = AtomicBoolean(true)

    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    private val scope = CoroutineScope(
        SupervisorJob() + dispatcher
    )

    private val fragmentHandler = FragmentHandler { buffer, offset, length, _ ->

        val message = buffer.getStringUtf8(offset, length)

        log.info("SETTLEMENT: {}", message)
    }

    @PostConstruct
    fun start() {

        scope.launch {

            val idleStrategy = BackoffIdleStrategy(
                1,
                10,
                1_000,
                100_000
            )

            while (running.get() && isActive) {

                try {

                    val fragmentsRead = subscription.poll(
                        fragmentHandler,
                        properties.pollFragmentLimit
                    )

                    idleStrategy.idle(fragmentsRead)

                } catch (e: Exception) {

                    log.error("Settlement engine failure", e)
                }
            }
        }
    }

    @PreDestroy
    fun stop() {
        log.info("Stopping settlement engine")
        running.set(false)

        scope.cancel()

        subscription.close()
        log.info("Settlement engine stopped")
    }
}