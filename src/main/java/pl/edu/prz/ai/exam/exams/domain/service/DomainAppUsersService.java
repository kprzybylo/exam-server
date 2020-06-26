package pl.edu.prz.ai.exam.exams.domain.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.prz.ai.exam.exams.domain.Group;
import pl.edu.prz.ai.exam.exams.domain.User;
import pl.edu.prz.ai.exam.exams.domain.exception.CouldNotAcquireLoggedUserException;
import pl.edu.prz.ai.exam.exams.domain.exception.GroupNotFoundException;
import pl.edu.prz.ai.exam.exams.domain.exception.UserNotFoundException;
import pl.edu.prz.ai.exam.exams.domain.repository.AppUserRepository;
import pl.edu.prz.ai.exam.exams.domain.repository.GroupRepository;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainAppUsersService implements AppUsersService {
    AppUserRepository appUserRepository;

    GroupRepository groupRepository;

    @Override
    public User getLoggedUser() {
        return appUserRepository.findByEmail(getLoggedUserEmail())
                .orElseThrow(CouldNotAcquireLoggedUserException::new);
    }

    @Override
    public Group findGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(GroupNotFoundException::new);
    }

    @Override
    public User findUser(Long userId) {
        return appUserRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
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
