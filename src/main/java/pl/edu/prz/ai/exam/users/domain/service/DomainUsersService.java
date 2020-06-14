package pl.edu.prz.ai.exam.users.domain.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.prz.ai.exam.users.application.request.CreateUser;
import pl.edu.prz.ai.exam.users.application.response.UserAccount;
import pl.edu.prz.ai.exam.users.domain.*;
import pl.edu.prz.ai.exam.users.domain.exception.CouldNotAssignRoleException;
import pl.edu.prz.ai.exam.users.domain.repository.RoleRepository;
import pl.edu.prz.ai.exam.users.domain.repository.UserRepository;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainUsersService implements UsersService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    UsersMapper usersMapper = new UsersMapper();

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
}
