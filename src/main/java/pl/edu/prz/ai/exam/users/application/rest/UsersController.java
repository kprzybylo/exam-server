package pl.edu.prz.ai.exam.users.application.rest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.ai.exam.users.application.request.ApproveAccount;
import pl.edu.prz.ai.exam.users.application.request.ChangeRole;
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

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveUserAccount(
            @PathVariable("id") Long userId,
            @Valid @RequestBody ApproveAccount approveAccount) {
        usersService.approveUserAccount(userId, approveAccount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/changeRole")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeUserRole(
            @PathVariable("id") Long userId,
            @Valid @RequestBody ChangeRole changeRole
    ) {
        usersService.changeUserRole(userId, changeRole);
        return ResponseEntity.ok().build();
    }
}
