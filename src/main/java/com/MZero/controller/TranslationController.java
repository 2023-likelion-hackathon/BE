package com.MZero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class TranslationController {
    private static final Pattern JOSA_PATTERN = Pattern.compile("웃프(다|네|)");
    private static final Map<Pattern, String> patternTranslations = new HashMap<>();
    private static final Map<String, String> spacedWordsTranslations = new HashMap<>();

    static {
        // 조사 패턴
        patternTranslations.put(JOSA_PATTERN, "웃긴데 사실 슬프$1");

        // 접두사, 접미사 패턴
        patternTranslations.put(Pattern.compile("\\b킹(\\w*?)"), "완전 $1");
        patternTranslations.put(Pattern.compile("(\\w*?)밍아웃"), "$1 공개한다");
        patternTranslations.put(Pattern.compile("(\\w*?)빠(\\w*?)"), "$1애호가$2");
        patternTranslations.put(Pattern.compile("\\b짱(\\w*?)"), "최고$1");
        patternTranslations.put(Pattern.compile("(\\w*?)충(\\w*?)"), "$1 하는 사람$2");
        patternTranslations.put(Pattern.compile("\\b캐(\\w*?)"), "엄청 $1");
        patternTranslations.put(Pattern.compile("\\b핵(\\w*?)"), "엄청 진짜 완전 $1");
        patternTranslations.put(Pattern.compile("(\\w*?)까(\\w*?)"), "$1 의견 반대$2");
        patternTranslations.put(Pattern.compile("\\b대깨(\\w*?)"), "어떤 일이 있어도 $1");
        patternTranslations.put(Pattern.compile("(\\w*?)각\\b"), "$1 할 수 있겠다");

        // 원래 띄어쓰기가 있는 단어
        spacedWordsTranslations.put("라떼는 말이야", "나 때는 말이야");
        spacedWordsTranslations.put("무슨 129", "무슨 일이야");
        spacedWordsTranslations.put("아무 말 대잔치", "아무말하기");
        spacedWordsTranslations.put("의문의 1승", "의도하지 않은 이득");
        spacedWordsTranslations.put("혈중 마라 농도", "마라 음식 섭취 빈도");
        spacedWordsTranslations.put("서로서로 도와요!", "함께 플레이");
        spacedWordsTranslations.put("누가 기침 소리를 내었어", "추궁");
    }

    @PostMapping("/translate")
    @ResponseBody
    public String translate(@RequestBody String text) {
        // 문자열을 1차로 필터링
        String filteredText = filtering(text);

        // 문자열을 공백 기준으로 나눔
        String[] words = filteredText.split(" ");

        // 엑셀에서 대체될 표준어를 찾아와서 신조어와 바꾸는 로직


        // 문장을 다시 합쳐서 반환(최종 결과)
        return String.join(" ", words);
    }

    // 1차로 문장을 필터링하기 위한 메소드
    public String filtering(String text) {
        // 띄어쓰기 있는 단어 변환
        for (Map.Entry<String, String> entry : spacedWordsTranslations.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }

        // 조사 및 접두사, 접미사 변환
        for (Map.Entry<Pattern, String> entry : patternTranslations.entrySet()) {
            Matcher matcher = entry.getKey().matcher(text);
            if (matcher.find()) {
                text = matcher.replaceAll(entry.getValue());
            }
        }
        return text;
    }
}
