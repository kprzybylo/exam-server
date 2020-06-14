package pl.edu.prz.ai.exam.users.domain.service;

import pl.edu.prz.ai.exam.users.application.request.ApproveAccount;
import pl.edu.prz.ai.exam.users.application.request.CreateUser;
import pl.edu.prz.ai.exam.users.application.response.UserAccount;

public interface UsersService {
    UserAccount registerUser(CreateUser createUser);

    void approveAccount(Long userId, ApproveAccount approveAccount);
}
