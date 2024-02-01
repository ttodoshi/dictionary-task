package org.example.utils.validators;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class DictionaryValidator {
    public boolean isWordValid(String word, String pattern) {
        Pattern r = Pattern.compile(pattern);
        return r.matcher(word).matches();
    }
}
