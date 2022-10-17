package com.censored.blooper.service;

import com.censored.blooper.dto.SensitiveWordDto;
import com.censored.blooper.model.SensitiveWord;

import java.util.List;
import java.util.Set;

public interface BleepService {
    String bleepWords(String sentence);

    Set<String> findWords(String[] words);

    String censorWords(Set<String> words);

    List<SensitiveWord> findAllBleeps();

    void saveList(Set<SensitiveWord> sensitiveWordSet);

    List<SensitiveWordDto> getAllBleeps();

    void addBleep(SensitiveWordDto sensitiveWordDto);

    void deleteWord(Long sensitiveWordId);
}
