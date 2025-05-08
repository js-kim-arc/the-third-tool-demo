package com.before.prac.Card.presentation;

import com.before.prac.Card.domain.Card;
import com.before.prac.Card.dto.CardStudyViewDto;
import com.before.prac.Card.dto.request.CardStudyResultRequest;
import com.before.prac.Card.dto.request.CardUpdateRequest;
import com.before.prac.Card.dto.response.ApiResponse;
import com.before.prac.Card.dto.response.CardResponse;
import com.before.prac.Card.application.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    //카드 수정하기
    @PatchMapping("/{cardId}")
    public ApiResponse<CardResponse> updateCard(@PathVariable Long cardId, @Valid @RequestBody CardUpdateRequest request) {
        Card card = cardService.updateCard(cardId, request);
        return ApiResponse.ok(CardResponse.from(card));
    }

    @DeleteMapping("/{cardId}")
    public ApiResponse<Void> deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        return ApiResponse.ok();
    }

    @GetMapping("/{cardId}/study")
    public ApiResponse<CardStudyViewDto> getStudyCard(@PathVariable Long cardId) {
        return ApiResponse.ok(cardService.getCardStudyView(cardId));
    }

    @PostMapping("/{cardId}/study/result")
    public ApiResponse<Void> studyResult(@PathVariable Long cardId, @Valid @RequestBody CardStudyResultRequest request) {
        cardService.processStudyResult(cardId, request.result());
        return ApiResponse.ok();
    }

    @PostMapping("/{cardId}/group")
    public ApiResponse<Void> setGroup(@PathVariable Long cardId, @RequestParam String group) {
        cardService.processDueGroupSelection(cardId, group);
        return ApiResponse.ok();
    }

    @GetMapping("/due")
    public ApiResponse<List<CardResponse>> getDueRange(@RequestParam int min, @RequestParam int max) {
        List<Card> cards = cardService.getCardsByDuePeriod(min, max);
        return ApiResponse.ok(cards.stream().map(CardResponse::from).toList());
    }
}
