package com.mapi.ihrd.module.employee.model;

public class PerformanceComponent {

    private String name;
    private int score;

    public PerformanceComponent(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
