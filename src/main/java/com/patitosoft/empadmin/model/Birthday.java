package com.patitosoft.empadmin.model;

import java.util.List;

public class Birthday {

    private List<Employee> today;
    private List<Employee> nextWeek;

    public Birthday(){}

    public Birthday(List<Employee> today, List<Employee> nextWeek) {
        this.today = today;
        this.nextWeek = nextWeek;
    }

    public List<Employee> getToday() {
        return today;
    }

    public void setToday(List<Employee> today) {
        this.today = today;
    }

    public List<Employee> getNextWeek() {
        return nextWeek;
    }

    public void setNextWeek(List<Employee> nextWeek) {
        this.nextWeek = nextWeek;
    }
}
