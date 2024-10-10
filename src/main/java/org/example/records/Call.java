package org.example.records;

import org.example.entity.PhoneCentral;

public record Call(PhoneCentral other, String message) {}
