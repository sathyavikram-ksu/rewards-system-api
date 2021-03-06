package org.rewards.system.models;

public enum PurchaseType {
    PAID(0),
    PRE_ORDER(1),
    MONTHLY_FREE_FOR_50(2),
    MONTHLY_FREE_FOR_GOLD(3);

    private int value;

    PurchaseType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
