package org.example;

import java.util.ArrayList;
import java.util.List;

public class Habit {
    private String name;
    private String description;
    private String frequency;
    private List<Boolean> records;

    public Habit(String name, String description, String frequency) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.records = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void recordCompletion(boolean completed) {
        records.add(completed);
    }

    public List<Boolean> getRecords() {
        return records;
    }

    public int getStreak() {
        int streak = 0;
        for (int i = records.size() - 1; i >= 0; i--) {
            if (records.get(i)) {
                streak++;
            } else {
                break;
            }
        }
        return streak;
    }

    public double getSuccessRate(int period) {
        int completed = 0;
        int total = records.size();

        if (total == 0) {
            return 0;
        }

        for (int i = total - period; i < total; i++) {
            if (i >= 0 && records.get(i)) {
                completed++;
            }
        }

        return (double) completed / period * 100;
    }

    public String getReport(int period) {
        int completed = 0;
        int total = records.size();

        for (int i = total - period; i < total; i++) {
            if (i >= 0 && records.get(i)) {
                completed++;
            }
        }

        return "Выполнено: " + completed + " из " + period + " (" + getSuccessRate(period) + "%)";
    }
}
