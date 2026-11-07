package com.amaansyed.relic

/**
 * Phone-only application state. A ready core is NOT a connected USB device.
 * Keep hardware state separate when USB support arrives in Slice 02.
 */
data class RelicSession(val coreActive: Boolean = false) {
    val headline: String
        get() = if (coreActive) "CORE ONLINE" else "AWAITING ACTIVATION"

    val actionLabel: String
        get() = if (coreActive) "RETURN TO STANDBY" else "ACTIVATE CORE"

    val detail: String
        get() = if (coreActive) "Local interface initialized" else "Manual startup available"

    val usbStatus: String
        get() = "NOT CONFIGURED"

    fun toggle(): RelicSession = copy(coreActive = !coreActive)
}
