package com.censored.blooperapp.service;

import com.censored.blooperapp.dto.SensitiveWordDto;
import com.censored.blooperapp.model.SensitiveWord;

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
