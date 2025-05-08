package com.before.prac.Card.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;

public record CardUpdateRequest(
        Long deckId,

        Long noteId,

        @Size(max = 1000, message = "카드 내용은 1000자를 초과할 수 없습니다.")
        String content
) {}