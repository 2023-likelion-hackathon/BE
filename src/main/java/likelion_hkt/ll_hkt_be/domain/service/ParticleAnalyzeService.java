package likelion_hkt.ll_hkt_be.domain.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParticleAnalyzeService {
    private static final Pattern PARTICLE_PATTERN = Pattern.compile("웃프(다|네|)"); // 조사
    private static final Map<Pattern, String> patternTranslations = new HashMap<>();
    private static final Map<String, String> spacedWordsTranslations = new HashMap<>();

    static {
        // 조사 패턴
        patternTranslations.put(PARTICLE_PATTERN, "웃프다");

        // 접두사, 접미사 패턴
        patternTranslations.put(Pattern.compile("킹(\\w*?)"), "킹 $1");
        patternTranslations.put(Pattern.compile("짱(\\w*?)"), "짱 $1");
        patternTranslations.put(Pattern.compile("캐(\\w*?)"), "캐- $1");
        patternTranslations.put(Pattern.compile("핵(\\w*?)"), "핵- $1");
        patternTranslations.put(Pattern.compile("대깨(\\w*?)"),"대깨- $1");
        patternTranslations.put(Pattern.compile("(.)?밍아웃"), "$1 -밍아웃");
        patternTranslations.put(Pattern.compile("(\\w*?)빠(\\w*?)"), "$1 빠 $2");
        patternTranslations.put(Pattern.compile("(\\w*?)충(\\w*?)"), "$1 -충 $2");
        patternTranslations.put(Pattern.compile("(\\w*?)까(\\w*?)"), "$1 -까 $2");
        patternTranslations.put(Pattern.compile("(\\w*?)각(\\w*?)"), "$1 -각 $2");


        spacedWordsTranslations.put("라떼는 말이야", "나 때는 말이야");
        spacedWordsTranslations.put("무슨 129", "무슨 일이야");
        spacedWordsTranslations.put("아무 말 대잔치", "아무말하기");
        spacedWordsTranslations.put("의문의 1승", "의도하지 않은 이득");
        spacedWordsTranslations.put("혈중 마라 농도", "마라 음식 섭취 빈도");
        spacedWordsTranslations.put("서로서로 도와요!", "다 같이 해요!");
        spacedWordsTranslations.put("누가 기침소리를 내었어", "누가 그랬어");
    }

    public String InitialSentenceAnalysis(String text) {
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
