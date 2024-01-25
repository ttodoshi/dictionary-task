package org.example.utils.validators;

import java.util.regex.Pattern;

public class FourLatinDictionaryValidator implements DictionaryValidator {
    @Override
    public boolean isWordValid(String word) {
        Pattern r = Pattern.compile("^[a-zA-Z]{4}$");
        return r.matcher(word).matches();
    }

    @Override
    public String getDictionaryName() {
        return "словарь 4 латинских букв";
    }
}
