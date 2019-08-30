package model;

import java.util.HashMap;

public class User {
    private static HashMap<String, User> instances = new HashMap<String, User>();

    public static User verify(String username, String password) {
        User user = getLogin(username);

        if (user != null && password.equals(user.getPassword())) {
            return user;
        }

        return null;
    }

    public static User getLogin(String username) {
        return instances.get(username);
    }

    private String username;
    private String password;

    public User(String user, String pass) {
        username = user.trim().toLowerCase();
        password = pass;

        instances.put(username, this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", username, password);
    }
}
