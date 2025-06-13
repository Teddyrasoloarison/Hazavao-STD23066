package com.hei.school.service;
import java.util.List;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGptClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getDefinition(String teny) {
        String prompt = "Hazavao amin'ny teny malagasy ilay teny hoe \"" + teny + "\".";

        String apiKey = System.getenv("OPENAI_API_KEY");
        String apiUrl = System.getenv().getOrDefault(
                "OPENAI_API_URL",
                "https://api.openai.com/v1/chat/completions"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(Map.of("role", "user", "content", prompt))
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, request, Map.class);

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        return (String) message.get("content");
    }
}
