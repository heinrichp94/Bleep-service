package com.censored.blooperapp.dto;

import com.censored.blooperapp.model.SensitiveWord;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link SensitiveWord} entity
 */
@Data
public class SensitiveWordDto implements Serializable {
    private final Long id;
    private final String bleepWord;

    public SensitiveWordDto(SensitiveWord sensitiveWord) {
        this.id = sensitiveWord.getId();
        this.bleepWord = sensitiveWord.getBleepWord();
    }
}