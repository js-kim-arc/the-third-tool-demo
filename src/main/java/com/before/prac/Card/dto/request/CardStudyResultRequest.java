package com.before.prac.Card.dto.request;

import com.before.prac.Card.domain.StudyResult;
import jakarta.validation.constraints.NotNull;

public record CardStudyResultRequest(
        @NotNull(message = "학습 결과는 필수입니다.")
        StudyResult result
) {}