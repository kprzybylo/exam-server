package pl.edu.prz.ai.exam.users.domain.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.prz.ai.exam.users.application.request.ApproveAccount;
import pl.edu.prz.ai.exam.users.application.request.ChangeRole;
import pl.edu.prz.ai.exam.users.application.request.CreateUser;
import pl.edu.prz.ai.exam.users.application.response.UserAccount;
import pl.edu.prz.ai.exam.users.domain.*;
import pl.edu.prz.ai.exam.users.domain.exception.CouldNotAssignRoleException;
import pl.edu.prz.ai.exam.users.domain.exception.UserNotFoundException;
import pl.edu.prz.ai.exam.users.domain.repository.RoleRepository;
import pl.edu.prz.ai.exam.users.domain.repository.UserRepository;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainUsersService implements UsersService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    UsersMapper usersMapper = new UsersMapper();

    @Override
    public UserAccount registerUser(CreateUser createUser) {
        Role studentRole = roleRepository.findByRoleName("STUDENT")
                .orElseThrow(CouldNotAssignRoleException::new);

        User user = usersMapper.fromCreateUserRequest(
                createUser,
                studentRole,
                bCryptPasswordEncoder.encode(createUser.getPassword())
        );
        userRepository.save(user);

        return usersMapper.toUserAccount(user);
    }

    @Override
    public void approveUserAccount(Long userId, ApproveAccount approveAccount) {
        Role newRole = roleRepository.findByRoleName(approveAccount.getNewRoleName())
                .orElseThrow(CouldNotAssignRoleException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User updatedUser = user.toBuilder()
                .isNonLocked(true)
                .role(newRole)
                .build();

        userRepository.save(updatedUser);
    }

    @Override
    public void changeUserRole(Long userId, ChangeRole changeRole) {
        Role newRole = roleRepository.findByRoleName(changeRole.getNewRoleName())
                .orElseThrow(CouldNotAssignRoleException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User updatedUser = user.toBuilder()
                .role(newRole)
                .build();

        userRepository.save(updatedUser);
    }
}
