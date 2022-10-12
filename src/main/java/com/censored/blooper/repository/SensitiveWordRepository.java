package com.censored.blooper.repository;

import com.censored.blooper.model.SensitiveWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SensitiveWordRepository extends JpaRepository<SensitiveWord, Long> {
    Set<SensitiveWord> findByBleepWordInIgnoreCase(String[] bleepWords);


}
