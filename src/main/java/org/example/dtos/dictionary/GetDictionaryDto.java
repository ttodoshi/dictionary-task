package org.example.dtos.dictionary;

import lombok.Data;

import java.util.UUID;

@Data
public class GetDictionaryDto {
    private UUID uuid;
    private String name;
}
