package com.codex.ecam.constants.budget;

import java.util.ArrayList;
import java.util.List;

public enum BudgetType {

    BUSINESS(0, "Business"),
    SUPPLIER(1, "Supplier");

    private Integer id;
    private String name;

    BudgetType(Integer id, String name) {
        setId(id);
        setName(name);
    }

    public static List<BudgetType> getBudgetTypes() {
        List<BudgetType> list = new ArrayList<BudgetType>();
        list.add(BudgetType.BUSINESS);
        list.add(BudgetType.SUPPLIER);
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
