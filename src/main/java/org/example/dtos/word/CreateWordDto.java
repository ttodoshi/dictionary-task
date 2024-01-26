package org.example.dtos.word;

import java.util.Objects;

public class CreateWordDto {
    private String word;
    private String translation;

    public CreateWordDto(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateWordDto word1 = (CreateWordDto) o;
        return Objects.equals(word, word1.word) && Objects.equals(translation, word1.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, translation);
    }

    @Override
    public String toString() {
        return "CreateWordDto{" +
                "word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
