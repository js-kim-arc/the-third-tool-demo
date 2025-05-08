package com.before.prac.Deck.presentation;

import com.before.prac.Card.domain.Card;
import com.before.prac.Deck.domain.Deck;
import com.before.prac.Card.dto.request.CardCreateRequest;
import com.before.prac.Card.dto.response.ApiResponse;
import com.before.prac.Card.dto.response.CardResponse;
import com.before.prac.Deck.dto.response.DeckResponse;
import com.before.prac.Card.application.CardService;
import com.before.prac.Deck.application.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeckController {

    private final DeckService deckService;
    private final CardService cardService;

    @GetMapping("/decks/roots")
    public ApiResponse<List<DeckResponse>> getRootDecks() {
        List<Deck> decks = deckService.getRootDecks();
        return ApiResponse.ok(decks.stream().map(DeckResponse::from).toList());
    }

    //permanent만 이용 가능
    @GetMapping("/decks/{deckId}/children")
    public ApiResponse<List<DeckResponse>> getChildren(@PathVariable Long deckId) {
        List<Deck> children = deckService.getImmediateChildren(deckId);
        return ApiResponse.ok(children.stream().map(DeckResponse::from).toList());
    }

    @PatchMapping("/decks/{deckId}/freeze")
    public ApiResponse<Void> freezeDeck(@PathVariable Long deckId) {
        deckService.freezeDeck(deckId);
        return ApiResponse.ok();
    }

    @PatchMapping("/decks/{deckId}/unfreeze")
    public ApiResponse<Void> unfreezeDeck(@PathVariable Long deckId) {
        deckService.unfreezeDeck(deckId);
        return ApiResponse.ok();
    }

    @GetMapping("/decks/{deckId}/cards/3day")
    public ApiResponse<List<CardResponse>> getCardsFor3Day(@PathVariable Long deckId) {
        List<Card> cards = deckService.getCardsFor3DayProject(deckId);
        return ApiResponse.ok(cards.stream().map(CardResponse::from).toList());
    }

    @GetMapping("/decks/{deckId}/cards/permanent")
    public ApiResponse<List<CardResponse>> getCardsForPermanent(@PathVariable Long deckId) {
        List<Card> cards = deckService.getArchivedCardsFromDeckTree(deckId);
        return ApiResponse.ok(cards.stream().map(CardResponse::from).toList());
    }

    @PostMapping("/decks/{deckId}/cards")
    public ApiResponse<CardResponse> createCard(@PathVariable Long deckId, @RequestBody CardCreateRequest request) {
        Card card = cardService.createCard(deckId, request.noteId(), request);
        return ApiResponse.ok(CardResponse.from(card));
    }
}