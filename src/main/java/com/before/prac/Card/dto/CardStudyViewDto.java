package com.before.prac.Card.dto;

import com.before.prac.Card.domain.Card;

public record CardStudyViewDto(
        Long cardId,
        String question,
        String answer,
        int intervalDays,
        boolean isArchived,
        int successCount,
        int reps,
        int easeFactor,
        int lapses
) {
    public static CardStudyViewDto from(Card card) {
        return new CardStudyViewDto(
                card.getId(),
                card.getNote().getQuestion(),
                card.getNote().getAnswer(),
                card.getIntervalDays(),
                card.isArchived(),
                card.getSuccessCount(),
                card.getReps(),
                card.getEaseFactor(),
                card.getLapses()
        );
    }
}
