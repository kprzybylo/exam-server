package pl.edu.prz.ai.exam.exams.domain.repository;

import pl.edu.prz.ai.exam.exams.domain.Attachment;

public interface AttachmentRepository {
    void save(Attachment attachment);

    Attachment findByQuestion_Id(Long questionId);

    void delete(Attachment attachment);
}
