package com.example.mutation;

/**
 * Applies discounts to an order total.
 * Rules:
 *  - Members get 10% off.
 *  - Orders of 100.00 or more get an extra 5% off.
 *  - Discount is capped at 50.00.
 */
public class DiscountService {

    static final double MEMBER_RATE = 0.10;
    static final double BULK_RATE = 0.05;
    static final double BULK_THRESHOLD = 100.0;
    static final double MAX_DISCOUNT = 50.0;

    public double discount(double total, boolean member) {
        if (total < 0) {
            throw new IllegalArgumentException("Total cannot be negative");
        }
        double rate = 0;
        if (member) {
            rate += MEMBER_RATE;
        }
        if (total >= BULK_THRESHOLD) {
            rate += BULK_RATE;
        }
        return Math.min(total * rate, MAX_DISCOUNT);
    }

    public double finalPrice(double total, boolean member) {
        return total - discount(total, member);
    }
}
