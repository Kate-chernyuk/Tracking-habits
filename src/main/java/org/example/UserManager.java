package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private Map<String, User> users = new HashMap<>();

    public boolean registerUser(String email, String password) {
        if (users.containsKey(email)) return false;
        users.put(email, new User(email, password));

        return true;
    }

    public User loginUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean updateUser(String email, String newName, String newPassword) {
        User user = users.get(email);
        if (user != null) {
            user.setName(newName);
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String email) {
        if (users.containsKey(email)) {
            users.remove(email);
            return true;
        }
        return false;
    }

    public User getUser(String email) {
        User user = users.get(email);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void blockUser(String email) {
        User user = users.get(email);
        if (user != null) {
            user.setActive(false);
        }
    }

    public void unblockUser(String email) {
        User user = users.get(email);
        if (user != null) {
            user.setActive(true);
        }
    }

}
