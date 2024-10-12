package org.example;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleUI {
    private UserManager userManager;
    private HabitManager habitManager;
    private User currentUser;

    public ConsoleUI(UserManager userManager, HabitManager habitManager) {
        this.userManager = userManager;
        this.habitManager = habitManager;
    }

    public void start(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                System.out.println("1. Регистрация");
                System.out.println("2. Вход");
                System.out.println("3. Выход");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Введите email:");
                        String regEmail = scanner.nextLine();
                        System.out.println("Введите пароль:");
                        String regPassword = scanner.nextLine();
                        if (userManager.registerUser(regEmail, regPassword)) {
                            System.out.println("Регистрация успешна.");
                        } else {
                            System.out.println("Пользователь с таким email уже существует.");
                        }
                        break;

                    case 2:
                        System.out.println("Введите email:");
                        String loginEmail = scanner.nextLine();
                        System.out.println("Введите пароль:");
                        String loginPassword = scanner.nextLine();
                        currentUser = userManager.loginUser(loginEmail, loginPassword);
                        if ((currentUser != null) &&(Objects.equals(currentUser.getEmail(), "admin@example.com")) && (Objects.equals(currentUser.getPassword(), "adminpass"))) {
                            System.out.println("Вход успешен. Добро пожаловать, админ");
                            adminMenu(scanner, admin);
                        } else if ((currentUser != null) && (currentUser.isActive())) {
                            System.out.println("Вход успешен. Добро пожаловать, " + currentUser.getEmail());
                            userMenu(scanner);
                        } else if ((currentUser != null) && (!currentUser.isActive())) {
                            System.out.println("Вы были заблокированны");
                            currentUser = null;
                        } else {
                            System.out.println("Неверный email или пароль.");
                        }
                        break;

                    case 3:
                        System.out.println("Выход из приложения.");
                        System.exit(0);
                }
            }
        }
    }

    private void userMenu(Scanner scanner) {
        while (currentUser != null) {
            System.out.println("\n1. Добавить привычку");
            System.out.println("2. Просмотреть привычки");
            System.out.println("3. Записать выполнение привычки");
            System.out.println("4. Удалить привычку");
            System.out.println("5. Редактировать привычку");
            System.out.println("6. Генерировать статистику");
            System.out.println("7. Редактировать профиль");
            System.out.println("8. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.println("Введите название привычки:");
                    String habitName = scanner.nextLine();
                    System.out.println("Введите описание:");
                    String habitDescription = scanner.nextLine();
                    System.out.println("Введите частоту:");
                    String frequency = scanner.nextLine();
                    habitManager.addHabit(currentUser, new Habit(habitName, habitDescription, frequency));
                    System.out.println("Привычка добавлена.");
                    break;

                case 2:
                    List<Habit> habits = habitManager.getUserHabits(currentUser);
                    System.out.println("Ваши привычки:");
                    for (Habit habit : habits) {
                        System.out.println("- " + habit.getName());
                    }
                    break;

                case 3:
                    System.out.println("Введите название привычки для отметки выполнения:");
                    String completedHabitName = scanner.nextLine();
                    if (habitManager.recordHabitCompletion(currentUser, completedHabitName, true)) {
                        System.out.println("Отметка о выполнении добавлена.");
                    } else {
                        System.out.println("Такой привычки нет");
                    }
                    break;

                case 4:
                    System.out.println("Введите название привычки для удаления:");
                    String habitToDelete = scanner.nextLine();
                    if (habitManager.deleteHabit(currentUser, habitToDelete)) {
                        System.out.println("Привычка удалена.");
                    } else {
                        System.out.println("Привычка не найдена.");
                    }
                    break;

                case 5:
                    System.out.println("Введите название привычки для редактирования:");
                    String oldHabitName = scanner.nextLine();
                    System.out.println("Введите новое название привычки:");
                    String newHabitName = scanner.nextLine();
                    System.out.println("Введите новое описание:");
                    String newHabitDescription = scanner.nextLine();
                    System.out.println("Введите новую частоту:");
                    String newFrequency = scanner.nextLine();
                    Habit newHabit = new Habit(newHabitName, newHabitDescription, newFrequency);
                    if (habitManager.editHabit(currentUser, oldHabitName, newHabit)) {
                        System.out.println("Привычка обновлена.");
                    } else {
                        System.out.println("Не удалось обновить привычку. Проверьте название.");
                    }
                    break;

                case 6:
                    System.out.println("Введите название привычки для генерации статистики:");
                    String statHabitName = scanner.nextLine();
                    System.out.println("Введите период (день = 1, неделя = 7, месяц = 30):");
                    int period = scanner.nextInt();
                    String report = habitManager.generateStatistics(currentUser, statHabitName, period);
                    System.out.println(report);
                    break;

                case 7:
                    System.out.println("Введите новое имя:");
                    String newName = scanner.nextLine();
                    System.out.println("Введите новый пароль:");
                    String newPassword = scanner.nextLine();
                    userManager.updateUser(currentUser.getEmail(), newName, newPassword);
                    currentUser.setName(newName);
                    System.out.println("Профиль обновлен.");
                    break;

                case 8:
                    currentUser = null;
                    System.out.println("Вы вышли из учетной записи.");
                    break;
            }
        }
    }

    private void adminMenu(Scanner scanner, Admin admin) {
        while (currentUser != null) {
            System.out.println("\n1. Просмотреть всех пользователей");
            System.out.println("2. Заблокировать пользователя");
            System.out.println("3. Разблокировать пользователя");
            System.out.println("4. Удалить пользователя");
            System.out.println("5. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<User> users = admin.viewAllUsers();
                    System.out.println("Список пользователей:");
                    for (User user : users) {
                        System.out.println("- " + user.getEmail() + (user.isActive() ? " (активен)" : " (заблокирован)"));
                    }
                    break;

                case 2:
                    System.out.println("Введите email пользователя для блокировки:");
                    String emailToBlock = scanner.nextLine();
                    admin.blockUser(emailToBlock);
                    System.out.println("Пользователь заблокирован.");
                    break;

                case 3:
                    System.out.println("Введите email пользователя для разблокировки:");
                    String emailToUnblock = scanner.nextLine();
                    admin.unblockUser(emailToUnblock);
                    System.out.println("Пользователь разблокирован.");
                    break;

                case 4:
                    System.out.println("Введите email пользователя для удаления:");
                    String emailToDelete = scanner.nextLine();
                    if (admin.deleteUser(emailToDelete)) {
                        System.out.println("Пользователь удален.");
                    } else {
                        System.out.println("Не удалось удалить пользователя.");
                    }
                    break;

                case 5:
                    currentUser = null;
                    System.out.println("Вы вышли из учетной записи.");
                    break;
            }
        }
    }

}
