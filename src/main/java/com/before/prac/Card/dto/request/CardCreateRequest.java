package com.before.prac.Card.dto.request;

import com.before.prac.Card.domain.Card;
import com.before.prac.Deck.domain.Deck;
import com.before.prac.Note.domain.Note;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.Instant;
import java.util.List;

public record CardCreateRequest(
        @NotNull(message = "노트 ID는 필수입니다.")
        Long noteId,

        @NotBlank(message = "카드 내용은 비워둘 수 없습니다.")
        String content,

        @Size(max = 10, message = "태그는 최대 10개까지 등록할 수 있습니다.")
        List<@NotBlank(message = "태그는 비워둘 수 없습니다.") String> tags
) {

        public static CardCreateRequest from(Card card) {
                return new CardCreateRequest(
                        card.getNote().getId(),
                        card.getContent(),
                        card.getTags()
                );
        }

        public static CardCreateRequest of(Long noteId, String content, List<String> tags) {
                return new CardCreateRequest(noteId, content, tags);
        }
        public Card toEntity(Deck deck, Note note) {
                return Card.builder()
                           .deck(deck)
                           .note(note)
                           .content(this.content)
                           .tags(this.tags)
                           .dueDate(Instant.now().plusSeconds(86400L)) // 기본 1일 후
                           .intervalDays(1)
                           .easeFactor(2500)
                           .successCount(0)
                           .reps(0)
                           .lapses(0)
                           .remainingSteps(0)
                           .build();
        }

}