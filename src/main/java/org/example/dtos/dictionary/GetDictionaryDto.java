package org.example.dtos.dictionary;

import java.util.Objects;

public class GetDictionaryDto {
    private String name;

    public GetDictionaryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetDictionaryDto that = (GetDictionaryDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "GetDictionaryDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
