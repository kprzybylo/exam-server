package pl.edu.prz.ai.exam.users.application.rest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.prz.ai.exam.users.application.request.CreateUser;
import pl.edu.prz.ai.exam.users.application.response.UserAccount;
import pl.edu.prz.ai.exam.users.domain.service.UsersService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsersController {
    UsersService usersService;

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<UserAccount> registerUser(@Valid @RequestBody CreateUser createUser) {
        return ResponseEntity.ok(usersService.registerUser(createUser));
    }
}
