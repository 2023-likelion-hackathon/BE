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
