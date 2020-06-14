package pl.edu.prz.ai.exam.groups.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.prz.ai.exam.groups.domain.service.DomainGroupsService;
import pl.edu.prz.ai.exam.groups.domain.service.GroupsService;

@Configuration
public class GroupsConfiguration {
    @Bean
    public GroupsService groupsService(
            SpringExamGroupRepository springExamGroupRepository,
            SpringGroupUserRepository springGroupUserRepository) {
        return new DomainGroupsService(springExamGroupRepository, springGroupUserRepository);
    }
}
