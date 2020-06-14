package pl.edu.prz.ai.exam.exams.infrastructure;

import org.springframework.data.repository.Repository;
import pl.edu.prz.ai.exam.exams.domain.Attachment;
import pl.edu.prz.ai.exam.exams.domain.repository.AttachmentRepository;

public interface SpringAttachmentRepository extends AttachmentRepository, Repository<Attachment, Long> {
}
