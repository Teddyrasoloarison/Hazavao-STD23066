package com.hei.school.service;

import com.hei.school.file.bucket.BucketComponent;
import java.io.File;
import java.time.Duration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HazavaoService {

    private final ChatGptClientService chatGptClient;
    private final FileService fileService;
    private final BucketComponent bucketComponent;

    public String hazavaoTeny(String teny) {
        try {
            String definition = chatGptClient.getDefinition(teny);
            File file = fileService.writeToTempFile("hazavao-" + teny, ".txt", definition);
            String bucketKey = "hazavao-" + teny + ".txt";

            bucketComponent.upload(file, bucketKey);
            return bucketComponent.presign(bucketKey, Duration.ofMinutes(5)).toString();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération de la définition", e);
        }
    }
}