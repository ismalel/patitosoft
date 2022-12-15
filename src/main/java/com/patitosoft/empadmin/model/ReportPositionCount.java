package com.patitosoft.empadmin.model;

import java.util.List;

public class ReportPositionCount {

    private Long totalCount;
    private String title;

    public ReportPositionCount() {}

    public ReportPositionCount(Long totalCount, String title) {
        this.totalCount = totalCount;
        this.title = title;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
