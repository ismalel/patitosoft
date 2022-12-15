package com.patitosoft.empadmin.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "position_history")
public class PositionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "position_history")
    private String position;

    public PositionHistory() {}

    public PositionHistory(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String positionHistory) {
        this.position = positionHistory;
    }
}
