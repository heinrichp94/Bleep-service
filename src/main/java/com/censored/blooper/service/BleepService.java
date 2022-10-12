package com.censored.blooper.service;

import com.censored.blooper.dto.SensitiveWordDto;
import com.censored.blooper.exceptions.ResourceNotFoundException;
import com.censored.blooper.model.SensitiveWord;
import com.censored.blooper.repository.SensitiveWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BleepService {

    private final SensitiveWordRepository sensitiveWordRepository;

    public String bleepWords(String sentence) {
        if (sentence == null || sentence.isBlank()) {
            throw new IllegalArgumentException("Sentence can't be blank");
        }
        String[] wordsInSentence = sentence.toUpperCase().split("\\s+");
        Set<String> foundBleepWords = findWords(wordsInSentence);
        if (foundBleepWords.size() < 1) {
            return sentence;
        }
        return sentence.replaceAll("(?i)" + censorWords(foundBleepWords), "*");

    }

    @Cacheable("SensitiveWord")
    public Set<String> findWords(String[] words) {
        return sensitiveWordRepository.findByBleepWordInIgnoreCase(words)
                .stream()
                .map(SensitiveWord::getBleepWord)
                .collect(Collectors.toSet());
    }

    public String censorWords(Set<String> words) {
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (sb.length() > 0) sb.append("|");
            sb.append(
                    String.format("(?<=(?=%s).{0,%d}).",
                            Pattern.quote(w),
                            w.length() - 1
                    )
            );
        }
        return sb.toString();
    }


    public List<SensitiveWord> findAllBleeps() {
        return sensitiveWordRepository.findAll();
    }

    public void saveList(Set<SensitiveWord> sensitiveWordSet) {
        sensitiveWordRepository.saveAll(sensitiveWordSet);
    }

    public List<SensitiveWordDto> getAllBleeps() {
        return findAllBleeps()
                .stream()
                .map(SensitiveWordDto::new)
                .collect(Collectors.toList());
    }

    public void addBleep(SensitiveWordDto sensitiveWordDto) {
        if (sensitiveWordDto == null || sensitiveWordDto.getBleepWord().isBlank()) {
            throw new IllegalArgumentException("Bleep word can't be blank");
        }
        Set<SensitiveWord> words = new HashSet<>();
        String[] wordsInSentence = sensitiveWordDto.getBleepWord().split("\\s+");
        for (String s : wordsInSentence) {//I saw the 'SELECT * FROM' too late 😅
            words.add(new SensitiveWord(s));
        }
        saveList(words);
    }

    public void deleteWord(Long sensitiveWordId) {
        SensitiveWord sensitiveWord = sensitiveWordRepository.findById(sensitiveWordId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Word not found");
        });
        sensitiveWordRepository.delete(sensitiveWord);
    }
}
