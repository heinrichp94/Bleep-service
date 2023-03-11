package com.censored.blooperapp.repository;

import com.censored.blooperapp.model.SensitiveWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SensitiveWordRepository extends JpaRepository<SensitiveWord, Long> {
    Set<SensitiveWord> findByBleepWordInIgnoreCase(String[] bleepWords);


}
