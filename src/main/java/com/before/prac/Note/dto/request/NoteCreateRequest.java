package com.before.prac.Note.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteCreateRequest {
    @NotBlank
    @Size(max = 1000)
    private String question;

    @NotBlank @Size(max = 1000)
    private String answer;
}