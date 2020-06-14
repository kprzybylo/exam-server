package pl.edu.prz.ai.exam.groups.domain.service;

import pl.edu.prz.ai.exam.groups.application.request.CreateGroup;
import pl.edu.prz.ai.exam.groups.application.response.Group;

public interface GroupsService {
    void addUserToGroup(Long groupId, Long userId);

    void removeUserFromGroup(Long groupId, Long userId);

    Group createNewExamGroup(CreateGroup createGroup);
}
