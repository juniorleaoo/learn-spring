package com.learning.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learning.user.User;
import com.learning.user.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GroupServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setName("user - 1");
        userService.createUser(user);

        assertNotNull(user.getId());

        User user2 = new User();
        user2.setName("user - 2");
        userService.createUser(user2);

        assertNotNull(user2.getId());

        Group group = new Group();
        group.setName("group - 1");
        group.setUsers(Arrays.asList(user, user2));

        Group groupResult = groupService.createGroup(group);

        assertNotNull(groupResult.getId());

        assertEquals(group.getName(), groupResult.getName());
        assertEquals(group.getUsers().size(), groupResult.getUsers().size());
    }

}
