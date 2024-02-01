package org.example.exceptions;

public class DictionaryNotFoundException extends RuntimeException {
    public DictionaryNotFoundException(String dictionary) {
        super(String.format("Словарь '%s' не найден", dictionary));
    }
}
