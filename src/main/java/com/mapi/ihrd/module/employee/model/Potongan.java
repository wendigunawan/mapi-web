package com.mapi.ihrd.module.employee.model;

import java.math.BigDecimal;

public class Potongan {

    private String name;
    private BigDecimal amount = new BigDecimal(0D);

    public Potongan(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
