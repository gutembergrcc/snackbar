package com.snackbar.application.core.domain.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class InstantUtils {

    private InstantUtils() {
    }

    public static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }
}
