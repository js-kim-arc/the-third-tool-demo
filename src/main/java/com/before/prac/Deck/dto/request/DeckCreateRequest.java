package com.before.prac.Deck.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeckCreateRequest {

    @NotBlank
    private String name;

    private Long parentId; // 루트 덱이면 null
}
