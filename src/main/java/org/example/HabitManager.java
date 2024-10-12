package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HabitManager {
    private Map<User, List<Habit>> userHabits = new HashMap<>();

    public void addHabit(User user, Habit habit) {
        userHabits.putIfAbsent(user, new ArrayList<>());
        userHabits.get(user).add(habit);
    }

    public boolean recordHabitCompletion(User user, String habitName, boolean completed) {
        List<Habit> habits = userHabits.get(user);
        if (habits != null) {
            for (Habit habit : habits) {
                if (habit.getName().equals(habitName)) {
                    habit.recordCompletion(completed);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Habit> getUserHabits(User user) {
        return userHabits.getOrDefault(user, new ArrayList<>());
    }

    public boolean deleteHabit(User user, String habitName) {
        List<Habit> habits = userHabits.get(user);
        if (habits != null) {
            return habits.removeIf(habit -> habit.getName().equals(habitName));
        }
        return false;
    }

    public boolean editHabit(User user, String oldName, Habit newHabit) {
        List<Habit> habits = userHabits.get(user);
        if (habits != null) {
            for (Habit habit : habits) {
                if (habit.getName().equals(oldName)) {
                    habit.setName(newHabit.getName());
                    habit.setDescription(newHabit.getDescription());
                    habit.setFrequency(newHabit.getFrequency());
                    return true;
                }
            }
        }
        return false;
    }

    public String generateStatistics(User user, String habitName, int period) {
        List<Habit> habits = userHabits.get(user);
        if (habits != null) {
            for (Habit habit : habits) {
                if (habit.getName().equals(habitName)) {
                    return habit.getReport(period);
                }
            }
        }
        return "Привычка не найдена.";
    }
}

