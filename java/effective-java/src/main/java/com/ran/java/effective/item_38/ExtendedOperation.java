package com.ran.java.effective.item_38;

/**
 * ExtendedOperation
 *
 * @author rwei
 * @since 2024/5/14 16:16
 */
public enum ExtendedOperation implements Operation {
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }
}
