package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("andrey", "ivanov", (byte) 12);
        userService.saveUser("Ivan", "Vasilev", (byte) 15);
        userService.saveUser("Ui", "fds", (byte) 20);
        userService.saveUser("Erzhan", "Erzhanov", (byte) 45);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
