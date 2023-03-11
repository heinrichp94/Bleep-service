package com.censored.blooperapp.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SensitiveWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true)
    private String bleepWord;

    public SensitiveWord(String bleepWord) {
        this.bleepWord = bleepWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensitiveWord that = (SensitiveWord) o;
        return Objects.equals(bleepWord, that.bleepWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bleepWord);
    }
}
