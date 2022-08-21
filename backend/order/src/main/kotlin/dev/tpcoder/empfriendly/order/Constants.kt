package dev.tpcoder.empfriendly.order

import com.github.f4b6a3.ulid.UlidCreator

object Constants {

    private const val DELIVERY_PREFIX: String = "dlv"
    private const val JOURNEY_PREFIX: String = "jrny"

    fun createDeliveryId(): String = "${DELIVERY_PREFIX}${UlidCreator.getUlid()}"

    fun createJourneyId(): String = "${JOURNEY_PREFIX}${UlidCreator.getUlid()}"
}