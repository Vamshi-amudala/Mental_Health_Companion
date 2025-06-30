package com.example.service;

import com.example.entity.MoodTimeSlot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

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
        String prompt = String.format(
            "Give a calming and short self-care suggestion for someone with mood level %d in the %s.\n" +
            "Respond like this:\n" +
            "Suggestion: [your suggestion]\n" +
            "Steps:\n1. Step one.\n2. Step two.\n3. Step three.\n" +
            "Do not include explanations or ask questions.",
            moodLevel, timeSlot.name().toLowerCase()
        );

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

            String raw = extractText(response.getBody());
            log.info("🤖 AI response: {}", raw);
            return raw.split("If you're not yet familiar|\\[")[0]; // Trim extras

        } catch (Exception e) {
            log.error("Hugging Face API failed", e);
            return "Suggestion: Sit quietly for 5 minutes.\nSteps:\n1. Find a peaceful spot.\n2. Close your eyes.\n3. Focus on your breathing.";
        }
    }

    private String extractText(String json) {
        int idx = json.indexOf("generated_text");
        if (idx == -1) return "";
        int start = json.indexOf(":", idx) + 2;
        int end = json.indexOf("\"", start);
        return json.substring(start, end).trim();
    }
}
