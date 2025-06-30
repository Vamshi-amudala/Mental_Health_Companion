package com.example.service;

import com.example.entity.MoodTimeSlot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class HuggingFaceAiService {

    @Value("${HUGGINGFACE_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public HuggingFaceAiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String API_URL =
            "https://api-inference.huggingface.co/models/HuggingFaceH4/zephyr-7b-beta";

    public String getSuggestion(int moodLevel, MoodTimeSlot timeSlot) {
        String prompt = String.format("""
            Mood Level: %d
            Time: %s

            Suggest one short calming self-care activity.

            Format your response exactly like this:
            Suggestion: <short activity>
            Steps:
            1. <step one>
            2. <step two>
            3. <step three>

            Do not add anything else.
            """, moodLevel, timeSlot.name().toLowerCase());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, String> body = Map.of("inputs", prompt);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                request,
                String.class
            );

            String raw = extractSuggestionBlock(response.getBody());
            log.info("🤖 AI response: {}", raw);

            if (raw == null || raw.isBlank()) {
                return """
                    Suggestion: Take a deep breath.
                    Steps:
                    1. Sit in a quiet place.
                    2. Inhale slowly.
                    3. Exhale fully.
                    """;
            }

            return raw;

        } catch (Exception e) {
            log.error("Hugging Face API failed", e);
            return """
                Suggestion: Sit quietly for 5 minutes.
                Steps:
                1. Find a peaceful spot.
                2. Close your eyes.
                3. Focus on your breathing.
                """;
        }
    }

    private String extractSuggestionBlock(String raw) {
        Pattern pattern = Pattern.compile("Suggestion:\\s*(.*?)\\s*Steps:\\s*1\\.\\s*(.*?)\\s*2\\.\\s*(.*?)\\s*3\\.\\s*(.*?)(\\n|$)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(raw);
        if (matcher.find()) {
            return String.format("Suggestion: %s\nSteps:\n1. %s\n2. %s\n3. %s",
                matcher.group(1).trim(),
                matcher.group(2).trim(),
                matcher.group(3).trim(),
                matcher.group(4).trim()
            );
        }
        return null;
    }
}
