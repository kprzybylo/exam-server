package pl.edu.prz.ai.exam.groups.domain.service;

import pl.edu.prz.ai.exam.groups.application.request.CreateGroup;
import pl.edu.prz.ai.exam.groups.application.response.Group;
import pl.edu.prz.ai.exam.groups.domain.ExamGroup;
import pl.edu.prz.ai.exam.groups.domain.User;

import java.util.stream.Collectors;

public class GroupsMapper {

    public ExamGroup fromCreateGroup(CreateGroup createGroup) {
        return ExamGroup.builder()
                .groupName(createGroup.getGroupName())
                .build();
    }

    public Group toGroup(ExamGroup examGroup) {
        return Group.builder()
                .id(examGroup.getId())
                .groupName(examGroup.getGroupName())
                .ownerId(examGroup.getOwner().getId())
                .students(
                        examGroup.getStudents() != null ? examGroup.getStudents()
                                .stream()
                                .map(User::getId)
                                .collect(Collectors.toList()) : null
                ).build();
    }
}
