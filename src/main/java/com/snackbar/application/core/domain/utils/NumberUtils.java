package com.snackbar.application.core.domain.utils;

import java.math.BigDecimal;

public class NumberUtils {

    public static boolean isNegative (BigDecimal b) {
        return b.signum () == -1;
    }
}
