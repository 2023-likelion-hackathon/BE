package com.MZero;
import likelion_hkt.ll_hkt_be.domain.service.ParticleAnalyzeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationControllerTest {
    private final ParticleAnalyzeService controller = new ParticleAnalyzeService();
    @Test
    public void testPatterns() {
        // 조사 패턴
        assertEquals("지금 좀 웃긴데 사실 슬프다", controller.InitialSentenceAnalysis("지금 좀 웃프다"));
        assertEquals("지금 좀 웃긴데 사실 슬프네", controller.InitialSentenceAnalysis("지금 좀 웃프네"));

        // 접두사, 접미사 패턴
        assertEquals("완전 받아", controller.InitialSentenceAnalysis("완전 받아"));
        assertEquals("너 공개한다 했구나!", controller.InitialSentenceAnalysis("너 덕밍아웃 했구나!"));
        assertEquals("너는 호날두애호가니? 메시애호가니?", controller.InitialSentenceAnalysis("너는 호날두빠니? 메시빠니?"));
        assertEquals("역시 최고준태야!", controller.InitialSentenceAnalysis("역시 짱준태야!"));
        assertEquals("너는 티모 하는 사람이구나!", controller.InitialSentenceAnalysis("너는 티모충이구나!"));
        assertEquals("진짜 엄청 짜증나 ㅋㅋ", controller.InitialSentenceAnalysis("진짜 캐짜증나 ㅋㅋ"));
        assertEquals("헐 엄청 진짜 완전 부럽다 ㅠㅠ", controller.InitialSentenceAnalysis("헐 핵부럽다 ㅠㅠ"));
        assertEquals("뉴진스 의견반대 너무 싫어", controller.InitialSentenceAnalysis("뉴진스까 너무 싫어"));
        assertEquals("지금은 어떤 일이 있어도 용병 해야해!", controller.InitialSentenceAnalysis("지금은 대깨용병 해야해!"));
        assertEquals("유튜브 할 수 있겠다!", controller.InitialSentenceAnalysis("유튜브 각이다!"));

        // 띄어쓰기 있는 단어
        assertEquals("어허 나 때는 말이야 다 그랬어!", controller.InitialSentenceAnalysis("어허 라떼는 말이야 다 그랬어!"));
        assertEquals("엥 무슨 일이야", controller.InitialSentenceAnalysis("엥 무슨 129"));
        assertEquals("아무말하기 하는거야?", controller.InitialSentenceAnalysis("아무 말 대잔치 하는거야?"));
        assertEquals("아싸 의도하지 않은 이득", controller.InitialSentenceAnalysis("아싸 의문의 1승"));
        assertEquals("마라 음식 섭취 빈도 떨어졌어", controller.InitialSentenceAnalysis("혈중 마라 농도 떨어졌어"));
        assertEquals("우리 다 같이 해요!", controller.InitialSentenceAnalysis("우리 서로서로 도와요!"));
        assertEquals("지금 누가 그랬어", controller.InitialSentenceAnalysis("지금 누가 기침 소리를 내었어"));
    }
}



