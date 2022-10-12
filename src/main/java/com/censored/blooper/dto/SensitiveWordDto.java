package com.censored.blooper.dto;

import com.censored.blooper.model.SensitiveWord;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.censored.blooper.model.SensitiveWord} entity
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