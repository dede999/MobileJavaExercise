package org.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneCentralTest {
    private PhoneCentral origin1;
    private PhoneCentral origin2;
    private PhoneCentral target1;

    @BeforeEach
    void setUp() {
        origin1 = new PhoneCentral();
        origin2 = new PhoneCentral();
        target1 = new PhoneCentral();
    }

    @Test
    @DisplayName("Check if the phone is busy")
    void isBusy() {
        assertFalse(origin1.isBusy());

        origin1.call(target1);
        assertTrue(origin1.isBusy());
    }

    @Test
    @DisplayName("Call with no message")
    void call() {
        origin1.call(target1);
        assertTrue(origin1.isBusy());
        assertEquals("Answering call ...", target1.answer());
    }

    @Test
    @DisplayName("Call a busy phone")
    void testCallingBusyPhone() {
        origin1.call(target1);
        target1.answer();
        origin2.call(target1, "Please call me back");
        assertTrue(target1.isBusy());
        assertEquals("Please call me back", target1.getVoiceMessages()[0]);
    }

    @Test
    @DisplayName("Call a busy phone with no message")
    void testCallingBusyPhoneWithNoMessage() {
        origin1.call(target1);
        target1.answer();
        origin2.call(target1);
        assertTrue(target1.isBusy());
        assertEquals(0, target1.getVoiceMessages().length);
    }

    @Test
    @DisplayName("Cannot call when you are in a call")
    void testCannotCallWhenInCall() {
        origin1.call(target1);
        origin1.call(origin2, "This call should not go through");

        assertEquals("No incoming calls", origin2.answer());
        assertEquals(0, origin2.getVoiceMessages().length);
    }

    @Test
    @DisplayName("Ending a received call")
    void endingReceivedCall() {
        origin1.call(target1);
        target1.answer();
        target1.endCall();
        assertFalse(origin1.isBusy());
        assertFalse(target1.isBusy());
    }

    @Test
    @DisplayName("Ending a made call")
    void endingMadeCall() {
        origin1.call(target1);
        target1.answer();
        origin1.endCall();
        assertFalse(origin1.isBusy());
        assertFalse(target1.isBusy());
    }

    @Test
    @DisplayName("Answer with no previous calls")
    void answerWithNoCalls() {
        assertEquals("No incoming calls", origin1.answer());
    }

    @Test
    @DisplayName("Answer with one previous call")
    void answerWithOneCall() {
        origin1.call(target1);
        assertEquals("Answering call ...", target1.answer());
    }

    @Test
    @DisplayName("Answer the first of two previous calls")
    void answerWithTwoCalls() {
        origin1.call(target1);
        origin2.call(target1, "Please call me back");
        assertEquals("Answering call ...", target1.answer());
        String[] messages = target1.getVoiceMessages();
        assertEquals(1, messages.length);
        assertEquals("Please call me back", messages[0]);
    }

    @Test
    @DisplayName("Answer the second of two previous calls")
    void answerWithTwoCalls2() {
        origin1.call(target1, "I'm waiting for your call");
        origin2.call(target1, "Please call me back");
        assertEquals("Answering call ...", target1.answer(1));
        String[] messages = target1.getVoiceMessages();
        assertEquals(1, messages.length);
        assertEquals("I'm waiting for your call", messages[0]);
    }

    @Test
    @DisplayName("Answering no calls")
    void answerNoCalls() {
        origin1.call(target1);
        assertEquals("Turning down all calls ...", target1.answer(-1));
        origin2.call(target1, "Please call me back");
        assertEquals("Turning down all calls ...", target1.answer(6));
        String[] messages = target1.getVoiceMessages();
        assertEquals(1, messages.length);
        assertFalse(target1.isBusy());
    }
}