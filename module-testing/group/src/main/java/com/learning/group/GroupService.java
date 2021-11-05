package com.learning.group;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroup(Long id) {
        return groupRepository.getById(id);
    }

    public Optional<Group> updateGroup(Long id, Group group) {
        return groupRepository.findById(id).map(record -> {
            record.setName(group.getName());
            record.setUsers(group.getUsers());
            return groupRepository.save(record);
        });
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

}
