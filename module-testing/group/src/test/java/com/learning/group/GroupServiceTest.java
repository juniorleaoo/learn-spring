package com.learning.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.learning.user.User;
import com.learning.user.UserService;

@Transactional
@SpringBootTest
class GroupServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Test
    void should_save_group_without_users() {
        Group group = new Group();
        group.setName("group - 1");

        Group groupResult = groupService.createGroup(group);

        assertNotNull(groupResult.getId());
        assertEquals(group.getName(), groupResult.getName());
        assertNull(groupResult.getUsers());
    }

    @Test
    void should_save_group_with_empty_user_list() {
        Group group = new Group();
        group.setName("group - 1");
        group.setUsers(new ArrayList<>());

        Group groupResult = groupService.createGroup(group);

        assertNotNull(groupResult.getId());
        assertEquals(group.getName(), groupResult.getName());
        assertNotNull(groupResult.getUsers());
        assertTrue(groupResult.getUsers().isEmpty());
    }

    @Test
    void should_save_group_with_two_users() {
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
        assertEquals(2, groupResult.getUsers().size());
    }

    @Test
    void should_find_group_by_id(){
        Group group = new Group();
        group.setName("group - 1");

        Group savedGroup = groupService.createGroup(group);

        Group foundGroup = groupService.getGroup(savedGroup.getId());

        assertNotNull(foundGroup);
        assertEquals(group.getName(), foundGroup.getName());
    }

    @Test
    void should_find_no_groups() {
        List<Group> groups = groupService.getAllGroups();

        assertNotNull(groups);
        assertTrue(groups.isEmpty());
    }

    @Test
    void should_find_all_groups() {
    	Group group1 = new Group();
    	group1.setName("group - 1");
    	groupService.createGroup(group1);

    	Group group2 = new Group();
    	group2.setName("group - 2");
    	groupService.createGroup(group2);

    	List<Group> groups = groupService.getAllGroups();

    	assertNotNull(groups);
    	assertEquals(2, groups.size());
    }

    @Test
    void should_update_group_name(){
        Group group = new Group();
        group.setName("group - 1");

        Group savedGroup = groupService.createGroup(group);
        savedGroup.setName("group - 2");

        Optional<Group> updatedGroupOptional = groupService.updateGroup(savedGroup.getId(), savedGroup);

        assertTrue(updatedGroupOptional.isPresent());
        assertEquals(savedGroup.getName(), updatedGroupOptional.get().getName());
        assertNull(updatedGroupOptional.get().getUsers());
    }

    @Test
    void should_update_group_users(){
        User user = new User();
        user.setName("user - 1");
        userService.createUser(user);

        User user2 = new User();
        user2.setName("user - 2");
        userService.createUser(user2);

        Group group = new Group();
        group.setName("group - 1");
        group.setUsers(new ArrayList<>());
        group.getUsers().add(user);
        group.getUsers().add(user2);

        Group savedGroup = groupService.createGroup(group);
        savedGroup.getUsers().clear();
        savedGroup.getUsers().add(user);

        Optional<Group> updatedGroupOptional = groupService.updateGroup(savedGroup.getId(), savedGroup);

        assertTrue(updatedGroupOptional.isPresent());
        assertEquals(savedGroup.getName(), updatedGroupOptional.get().getName());
        assertEquals(1, updatedGroupOptional.get().getUsers().size());
    }

    @Test
    void should_delete_group_by_id(){
        User user = new User();
        user.setName("user - 1");
        userService.createUser(user);

        User user2 = new User();
        user2.setName("user - 2");
        userService.createUser(user2);

        Group group = new Group();
        group.setName("group - 1");
        group.setUsers(new ArrayList<>());
        group.getUsers().add(user);
        group.getUsers().add(user2);

        Group savedGroup = groupService.createGroup(group);

        groupService.deleteGroup(savedGroup.getId());

        List<Group> groups = groupService.getAllGroups();
        assertTrue(groups.isEmpty());

        List<User> users = userService.getAllUsers();
        assertTrue(users.isEmpty());
    }

}
