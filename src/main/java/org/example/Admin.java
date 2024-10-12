package org.example;

import java.util.List;

public class Admin {
    private UserManager userManager;

    public Admin(UserManager userManager) {
        this.userManager = userManager;
    }

    public List<User> viewAllUsers() {
        return userManager.getAllUsers();
    }

    public void blockUser(String email) {
        userManager.blockUser(email);
    }

    public void unblockUser(String email) {
        userManager.unblockUser(email);
    }

    public boolean deleteUser(String email) {
        return userManager.deleteUser(email);
    }
}
