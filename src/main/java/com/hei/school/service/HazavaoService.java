package com.hei.school.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HazavaoService {

    private final ChatGptClientService chatGptClient;

    public String hazavaoTeny(String teny) {
        return chatGptClient.getDefinition(teny);
    }
}
