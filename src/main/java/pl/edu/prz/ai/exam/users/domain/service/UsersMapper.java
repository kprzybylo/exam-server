package pl.edu.prz.ai.exam.users.domain.service;

import pl.edu.prz.ai.exam.users.application.request.CreateUser;
import pl.edu.prz.ai.exam.users.application.response.UserAccount;
import pl.edu.prz.ai.exam.users.domain.Role;
import pl.edu.prz.ai.exam.users.domain.User;

class UsersMapper {
    UserAccount toUserAccount(User user) {
        return UserAccount.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roleName(user.getRole().getRoleName())
                .isNonLocked(user.getIsNonLocked())
                .isEnabled(user.getIsEnabled())
                .build();
    }

    User fromCreateUserRequest(CreateUser createUser, Role role, String encryptedPassword) {
        return User.builder()
                .firstName(createUser.getFirstName())
                .lastName(createUser.getLastName())
                .email(createUser.getEmail())
                .role(role)
                .isEnabled(true)
                .isNonLocked(false)
                .password(encryptedPassword)
                .build();
    }
}
