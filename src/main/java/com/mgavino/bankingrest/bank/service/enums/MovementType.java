package com.mgavino.bankingrest.bank.service.enums;

public enum MovementType {
    DEPOSIT(1), WITHDRAW(-1);

    private int multiplier;

    private MovementType(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
