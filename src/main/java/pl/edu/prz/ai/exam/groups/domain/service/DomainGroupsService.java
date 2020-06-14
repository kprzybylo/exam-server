package pl.edu.prz.ai.exam.groups.domain.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.prz.ai.exam.groups.application.request.CreateGroup;
import pl.edu.prz.ai.exam.groups.application.response.Group;
import pl.edu.prz.ai.exam.groups.domain.ExamGroup;
import pl.edu.prz.ai.exam.groups.domain.User;
import pl.edu.prz.ai.exam.groups.domain.exception.GroupNotFoundException;
import pl.edu.prz.ai.exam.groups.domain.exception.UserNotFoundException;
import pl.edu.prz.ai.exam.groups.domain.repository.ExamGroupRepository;
import pl.edu.prz.ai.exam.groups.domain.repository.GroupUserRepository;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainGroupsService implements GroupsService {
    ExamGroupRepository examGroupRepository;
    GroupUserRepository groupUserRepository;

    GroupsMapper groupsMapper = new GroupsMapper();

    @Override
    @Transactional
    public void addUserToGroup(Long groupId, Long userId) {
        User user = groupUserRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        ExamGroup examGroup = examGroupRepository.findById(groupId)
                .orElseThrow(GroupNotFoundException::new);

        examGroup.getStudents().add(user);
        examGroupRepository.save(examGroup);
    }

    @Override
    @Transactional
    public void removeUserFromGroup(Long groupId, Long userId) {
        User user = groupUserRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        ExamGroup examGroup = examGroupRepository.findById(groupId)
                .orElseThrow(GroupNotFoundException::new);

        examGroup.getStudents().remove(user);
        examGroupRepository.save(examGroup);
    }

    @Override
    @Transactional
    public Group createNewExamGroup(CreateGroup createGroup) {
        ExamGroup examGroup = groupsMapper.fromCreateGroup(createGroup);

        User owner = groupUserRepository.findByEmail(getLoggedUserEmail());

        examGroup = examGroup.toBuilder()
                .owner(owner)
                .build();

        examGroupRepository.save(examGroup);

        return groupsMapper.toGroup(examGroup);
    }

    private String getLoggedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }
}
