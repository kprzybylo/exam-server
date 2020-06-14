package pl.edu.prz.ai.exam.users.application.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiError {
    long timestamp;
    int status;
    String error;
    String message;
}
