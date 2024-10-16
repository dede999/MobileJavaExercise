package org.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPhoneTest {
    private IPhone iPhone;

    @BeforeEach
    void setUp() {
        iPhone = new IPhone("11 Pro", "Midnight Green", 999);
    }

    @Test
    @DisplayName("Test IPhone constructor (model)")
    void getModel() {
        assertEquals("11 Pro", iPhone.getModel());
    }

    @Test
    @DisplayName("Test IPhone constructor (color)")
    void getColor() {
        assertEquals("Midnight Green", iPhone.getColor());
    }

    @Test
    @DisplayName("Test IPhone constructor (price)")
    void getPrice() {
        assertEquals(999, iPhone.getPrice());
    }

    @Test
    @DisplayName("Test IPhone constructor (musicPlayer)")
    void getMusicPlayer() {
        assertNotNull(iPhone.getMusicPlayer());
    }

    @Test
    @DisplayName("Test IPhone constructor (phone)")
    void getPhone() {
        assertNotNull(iPhone.getPhone());
    }

    @Test
    @DisplayName("Test IPhone constructor (safari)")
    void getSafari() {
        assertNotNull(iPhone.getSafari());
    }
}