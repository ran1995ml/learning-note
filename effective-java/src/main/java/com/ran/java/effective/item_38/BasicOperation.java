package com.ran.java.effective.item_38;

/**
 * BasicOperation
 *
 * @author rwei
 * @since 2024/5/14 16:12
 */
public enum BasicOperation implements Operation {

    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    }
    ;

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }
}
