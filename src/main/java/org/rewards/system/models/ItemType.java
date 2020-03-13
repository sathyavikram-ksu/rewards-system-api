package org.rewards.system.models;

public enum ItemType {
    ICE_CREAM(0), YOGURT(1);

    private int value;

    ItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
