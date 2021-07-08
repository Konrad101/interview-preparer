package pl.interviewhelpers.interviewpreparer.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPatchRequest {
    private final static String AVAILABLE_OPERATION_NAME = "replace";
    private final static String PHONE_OPERATION = "/phoneNumber";
    private final static String EMAIL_OPERATION = "/email";
    private String op;
    private String path;
    private String value;

    public boolean isOperationAvailable() {
        return op != null &&
                op.equalsIgnoreCase(AVAILABLE_OPERATION_NAME);
    }

    public boolean isPathValid() {
        if (path == null)
            return false;

        return path.equalsIgnoreCase(PHONE_OPERATION) ||
                path.equalsIgnoreCase(EMAIL_OPERATION);
    }

    public UserPatchOperation getPatchOperation() {
        if (path.equalsIgnoreCase(PHONE_OPERATION)) {
            return UserPatchOperation.CHANGE_PHONE_NUMBER;
        } else if (path.equalsIgnoreCase(EMAIL_OPERATION)) {
            return UserPatchOperation.CHANGE_EMAIL;
        }

        return null;
    }
}
