package com.learning.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void should_save_user() {
        User user = new User();
        user.setName("user - 1");

        User savedUser = userService.createUser(user);

        assertNotNull(savedUser.getId());
        assertEquals(savedUser.getName(), user.getName());
    }

    @Test
    void should_find_user_by_id() {
        User user = new User();
        user.setName("user - 1");

        User savedUser = userService.createUser(user);

        User foundUser = userService.getUser(savedUser.getId());

        assertNotNull(foundUser);
        assertEquals(foundUser.getName(), user.getName());
    }

    @Test
    void should_find_no_users() {
        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @Test
    void should_find_all_users(){
        User user1 = new User();
        user1.setName("user - 1");
        User user2 = new User();
        user2.setName("user - 2");
        User user3 = new User();
        user3.setName("user - 3");

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    void should_update_user_name(){
        User user = new User();
        user.setName("user - 1");

        User savedUser = userService.createUser(user);

        savedUser.setName("user - 2");

        Optional<User> updateUserOptional = userService.updateUser(savedUser.getId(), savedUser);

        assertTrue(updateUserOptional.isPresent());
        assertEquals(savedUser.getName(), updateUserOptional.get().getName());
    }

    @Test
    void should_delete_user_by_id(){
        User user = new User();
        user.setName("user - 1");

        User savedUser = userService.createUser(user);

        userService.deleteUser(savedUser.getId());

        List<User> users = userService.getAllUsers();
        assertTrue(users.isEmpty());
    }

}
