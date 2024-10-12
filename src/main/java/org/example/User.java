package org.example;

public class User {
    private String email;
    private String password;
    private String name;
    private boolean isActive;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.isActive = true;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
