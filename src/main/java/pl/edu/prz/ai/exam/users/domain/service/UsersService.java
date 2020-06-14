package pl.edu.prz.ai.exam.users.domain.service;

import pl.edu.prz.ai.exam.users.application.request.ApproveAccount;
import pl.edu.prz.ai.exam.users.application.request.ChangeRole;
import pl.edu.prz.ai.exam.users.application.request.CreateUser;
import pl.edu.prz.ai.exam.users.application.response.UserAccount;

public interface UsersService {
    UserAccount registerUser(CreateUser createUser);

    void approveUserAccount(Long userId, ApproveAccount approveAccount);

    void changeUserRole(Long userId, ChangeRole changeRole);

    void disableUserAccount(Long userId);
}
