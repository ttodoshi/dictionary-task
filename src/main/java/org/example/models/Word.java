package org.example.models;

import java.util.Objects;

public class Word {
    private String dictionaryName;
    private String word;
    private String translation;

    public Word(String dictionaryName, String word, String translation) {
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
        Word word1 = (Word) o;
        return Objects.equals(dictionaryName, word1.dictionaryName) && Objects.equals(word, word1.word) && Objects.equals(translation, word1.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dictionaryName, word, translation);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s\n", dictionaryName, word, translation);
    }
}
