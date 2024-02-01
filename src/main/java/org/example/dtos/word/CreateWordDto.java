package org.example.dtos.word;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateWordDto {
    private String word;
    @NotBlank
    private String translation;
}
