package pl.interviewhelpers.interviewpreparer.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRequest {
    private String content;
    private String answer;
    private String ownerUsername;
    private String programmingLanguage;
    private String categoryName;
}
