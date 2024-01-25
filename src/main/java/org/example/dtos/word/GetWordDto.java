package org.example.dtos.word;

import java.util.Objects;

public class GetWordDto {
    private String dictionaryName;
    private String word;
    private String translation;

    public GetWordDto(String dictionaryName, String word, String translation) {
        this.dictionaryName = dictionaryName;
        this.word = word;
        this.translation = translation;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
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
        GetWordDto word1 = (GetWordDto) o;
        return Objects.equals(dictionaryName, word1.dictionaryName) && Objects.equals(word, word1.word) && Objects.equals(translation, word1.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dictionaryName, word, translation);
    }

    @Override
    public String toString() {
        return "GetWordDto{" +
                "dictionaryName='" + dictionaryName + '\'' +
                ", word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
