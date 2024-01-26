package org.example.exceptions;

public class DictionaryNotFoundException extends RuntimeException {
    public DictionaryNotFoundException(String dictionaryName) {
        super(String.format("Словарь '%s' не найден", dictionaryName));
    }
}
