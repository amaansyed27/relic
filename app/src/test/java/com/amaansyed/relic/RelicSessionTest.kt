package com.amaansyed.relic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RelicSessionTest {
    @Test fun initialStateIsDormantAndHonest() {
        val session = RelicSession()
        assertFalse(session.coreActive)
        assertEquals("AWAITING ACTIVATION", session.headline)
        assertEquals("NOT CONFIGURED", session.usbStatus)
    }

    @Test fun manualActivationDoesNotFakeUsbConnection() {
        val ready = RelicSession().toggle()
        assertTrue(ready.coreActive)
        assertEquals("CORE ONLINE", ready.headline)
        assertEquals("NOT CONFIGURED", ready.usbStatus)
    }

    @Test fun activationCanBeReversed() {
        assertEquals(RelicSession(), RelicSession().toggle().toggle())
    }
}
