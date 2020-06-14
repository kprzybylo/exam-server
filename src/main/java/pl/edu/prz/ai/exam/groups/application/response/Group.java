package pl.edu.prz.ai.exam.groups.application.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Group {
    Long id;
    String groupName;
    Long ownerId;
    List<Long> students;
}
