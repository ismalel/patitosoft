package com.patitosoft.empadmin.model;

import java.util.List;

public class PositionCount {

    private int totalCount;
    private List<Employee> detail;

    public PositionCount(int totalCount, List<Employee> detail) {
        this.totalCount = totalCount;
        this.detail = detail;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Employee> getDetail() {
        return detail;
    }

    public void setDetail(List<Employee> detail) {
        this.detail = detail;
    }
}
