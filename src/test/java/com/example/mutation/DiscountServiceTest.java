package com.example.mutation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {

    private static final double DELTA = 0.0001;
    private final DiscountService service = new DiscountService();

    @Test
    void noDiscountForNonMemberSmallOrder() {
        assertEquals(0, service.discount(50, false), DELTA);
    }

    @Test
    void memberGetsTenPercent() {
        assertEquals(5, service.discount(50, true), DELTA);
    }

    @Test
    void bulkDiscountStartsExactlyAtThreshold() {
        assertEquals(5, service.discount(100, false), DELTA);
        assertEquals(0, service.discount(99.99, false), DELTA);
    }

    @Test
    void memberAndBulkDiscountsStack() {
        assertEquals(30, service.discount(200, true), DELTA);
    }

    @Test
    void discountIsCapped() {
        assertEquals(50, service.discount(1000, true), DELTA);
    }

    @Test
    void rejectsNegativeTotal() {
        assertThrows(IllegalArgumentException.class, () -> service.discount(-1, true));
        assertEquals(0, service.discount(0, true), DELTA);
    }

    @Test
    void finalPriceSubtractsDiscount() {
        assertEquals(170, service.finalPrice(200, true), DELTA);
        assertEquals(50, service.finalPrice(50, false), DELTA);
    }
}
