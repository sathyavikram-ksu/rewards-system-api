package org.rewards.system.models;

public enum PurchaseType {
    ICE_CREAM(0), YOGUT(1);

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
