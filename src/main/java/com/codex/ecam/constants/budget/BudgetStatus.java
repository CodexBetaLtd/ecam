package com.codex.ecam.constants.budget;

import java.util.ArrayList;
import java.util.List;

public enum BudgetStatus {

    BUSINESS(0, "Business"),
    SUPPLIER(1, "Supplier");

    private Integer id;
    private String name;

    BudgetStatus(Integer id, String name) {
        setId(id);
        setName(name);
    }

    public static List<BudgetStatus> getBusinessTypes() {
        List<BudgetStatus> list = new ArrayList<BudgetStatus>();
        list.add(BudgetStatus.BUSINESS);
        list.add(BudgetStatus.SUPPLIER);
        return list;
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

}
