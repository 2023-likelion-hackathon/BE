package com.MZero;
import com.MZero.controller.TranslationController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationControllerTest {
    private final TranslationController controller = new TranslationController();
    @Test
    public void testPatterns() {
        // 조사 패턴
        assertEquals("지금 좀 웃긴데 사실 슬프다", controller.filtering("지금 좀 웃프다"));
        assertEquals("지금 좀 웃긴데 사실 슬프네", controller.filtering("지금 좀 웃프네"));

        // 접두사, 접미사 패턴
        assertEquals("완전 받아", controller.filtering("킹받아"));
        assertEquals("너 취향 공개 했구나!", controller.filtering("너 덕밍아웃 했구나!"));
        assertEquals("너는 호날두팬이니? 메시팬이니?", controller.filtering("너는 호날두빠니? 메시빠니?"));
        assertEquals("역시 최고준태야!", controller.filtering("역시 짱준태야!"));
        assertEquals("너는 티모 하는 사람이구나!", controller.filtering("너는 티모충이구나!"));
        assertEquals("진짜 엄청 짜증나 ㅋㅋ", controller.filtering("진짜 캐짜증나 ㅋㅋ"));
        assertEquals("헐 진짜 부럽다 ㅠㅠ", controller.filtering("헐 핵부럽다 ㅠㅠ"));
        assertEquals("뉴진스 안티팬 너무 싫어", controller.filtering("뉴진스까 너무 싫어"));
        assertEquals("지금은 무조건 용병 해야해!", controller.filtering("지금은 대깨용병 해야해!"));
        assertEquals("유튜브 할 수 있겠다!", controller.filtering("유튜브 각이다!"));

        // 띄어쓰기 있는 단어
        assertEquals("어허 나 때는 말이야 다 그랬어!", controller.filtering("어허 라떼는 말이야 다 그랬어!"));
        assertEquals("엥 무슨 일이야", controller.filtering("엥 무슨 129"));
        assertEquals("아무말하기 하는거야?", controller.filtering("아무 말 대잔치 하는거야?"));
        assertEquals("아싸 의도하지 않은 이득", controller.filtering("아싸 의문의 1승"));
        assertEquals("마라 음식 섭취 빈도 떨어졌어", controller.filtering("혈중 마라 농도 떨어졌어"));
        assertEquals("우리 다 같이 해요!", controller.filtering("우리 서로서로 도와요!"));
        assertEquals("지금 누가 그랬어", controller.filtering("지금 누가 기침 소리를 내었어"));
    }
}



