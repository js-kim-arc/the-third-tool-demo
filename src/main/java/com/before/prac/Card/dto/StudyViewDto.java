package com.before.prac.Card.dto;

import com.before.prac.Card.domain.Card;

public class StudyViewDto {

    private String question;
    private String answer;
    private String statusLabel; // "다시 설정하세요" 또는 "현재 학습 간격: 3일"
    private boolean showIntervalSelector;

    public StudyViewDto(String question, String answer, String s, boolean b) {
    }

    public static StudyViewDto threeDayView(Card card) {
        return new StudyViewDto(
                card.getNote().getQuestion(),
                card.getNote().getAnswer(),
                "현재 학습 간격: " + card.getIntervalDays() + "일",
                false
        );
    }

    public static StudyViewDto permanentView(Card card) {
        return new StudyViewDto(
                card.getNote().getQuestion(),
                card.getNote().getAnswer(),
                "이 카드를 다시 학습하시겠어요?",
                true // 기간 선택 UI 표시
        );
    }

    // 생성자 생략
}