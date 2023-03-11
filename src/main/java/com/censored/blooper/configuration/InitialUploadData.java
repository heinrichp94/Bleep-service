package com.censored.blooper.configuration;

import com.censored.blooper.model.SensitiveWord;
import com.censored.blooper.service.BleepService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class InitialUploadData {
    private final BleepService bleepService;

    @PostConstruct
    public void Init() {
        if (!bleepService.findAllBleeps().isEmpty()) return;
        log.debug("Uploading bad words init");
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<String>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/json/SensitiveWords.json");
        try {
            List<String> words = mapper.readValue(inputStream, typeReference);
            Set<SensitiveWord> sensitiveWordSet = new HashSet<>();
            for (String word : words) {
                String[] wordsInSentence = word.split("\\s+");
                for (String s : wordsInSentence) {//I saw the 'SELECT * FROM' too late 😅
                    sensitiveWordSet.add(new SensitiveWord(s));
                }
            }
            bleepService.saveList(sensitiveWordSet);
            System.out.println("Words Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save Words: " + e.getMessage());
        }
    }

}
