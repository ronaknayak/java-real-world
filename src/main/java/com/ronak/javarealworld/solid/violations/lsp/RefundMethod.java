package com.ronak.javarealworld.solid.violations.lsp;

import java.math.BigDecimal;

public interface RefundMethod {
    String refundToOriginalMethod(BigDecimal amount);
}
