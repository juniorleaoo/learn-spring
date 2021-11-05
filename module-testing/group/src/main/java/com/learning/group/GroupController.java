package com.learning.group;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.group.Group;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(Group group) {
        group = groupService.createGroup(group);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(Long id) {
        return ResponseEntity.ok(groupService.getGroup(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(Long id, Group group) {
        return groupService.updateGroup(id, group)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
