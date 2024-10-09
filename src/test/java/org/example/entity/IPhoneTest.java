package org.example.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPhoneTest {

    @Test
    void getModel() {
        IPhone iPhone = new IPhone("11 Pro", "Midnight Green", 999);
        assertEquals("11 Pro", iPhone.getModel());
    }

    @Test
    void getColor() {
        IPhone iPhone = new IPhone("11 Pro", "Midnight Green", 999);
        assertEquals("Midnight Green", iPhone.getColor());
    }

    @Test
    void getPrice() {
        IPhone iPhone = new IPhone("11 Pro", "Midnight Green", 999);
        assertEquals(999, iPhone.getPrice());
    }

}