package com.hei.school.service;
import static java.io.File.createTempFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    public File writeToTempFile(String prefix, String suffix, String content) throws IOException {
        File file = createTempFile(prefix, suffix);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
        return file;
    }
}
