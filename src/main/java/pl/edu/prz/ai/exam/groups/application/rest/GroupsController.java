package pl.edu.prz.ai.exam.groups.application.rest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.prz.ai.exam.groups.application.request.UserAssignmentChange;
import pl.edu.prz.ai.exam.groups.application.request.CreateGroup;
import pl.edu.prz.ai.exam.groups.application.response.Group;
import pl.edu.prz.ai.exam.groups.domain.service.GroupsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/groups")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GroupsController {
    GroupsService groupsService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Group> createExamGroup(@Valid @RequestBody CreateGroup createGroup) {
        return ResponseEntity.ok(groupsService.createNewExamGroup(createGroup));
    }

    @PostMapping("{id}/addUser")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> addUserToGroup(
            @PathVariable("id") Long groupId,
            @Valid @RequestBody UserAssignmentChange userAssignmentChange) {
        groupsService.addUserToGroup(groupId, userAssignmentChange.getUserId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/removeUser")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> removeUserFromGroup(
            @PathVariable("id") Long groupId,
            @Valid @RequestBody UserAssignmentChange userAssignmentChange) {
        groupsService.removeUserFromGroup(groupId, userAssignmentChange.getUserId());

        return ResponseEntity.ok().build();
    }
}
