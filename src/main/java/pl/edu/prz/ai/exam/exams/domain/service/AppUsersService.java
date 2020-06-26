package pl.edu.prz.ai.exam.exams.domain.service;

import pl.edu.prz.ai.exam.exams.domain.Group;
import pl.edu.prz.ai.exam.exams.domain.User;

public interface AppUsersService {
    User getLoggedUser();

    User findUser(Long userId);

    Group findGroup(Long groupId);
}
