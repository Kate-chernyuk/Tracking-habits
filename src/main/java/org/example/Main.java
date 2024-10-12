package org.example;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        HabitManager habitManager = new HabitManager();
        ConsoleUI ui = new ConsoleUI(userManager, habitManager);

        userManager.registerUser("admin@example.com", "adminpass");
        Admin admin = new Admin(userManager);
        ui.start(admin);

    }
}
