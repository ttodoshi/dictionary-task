package org.example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @ManyToOne(optional = false)
    @JoinColumn(name = "dictionary_uuid", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private Dictionary dictionary;
    @Column(name = "dictionary_uuid")
    private UUID dictionaryUuid;
    private String word;
    private String translation;

    public Word(UUID dictionaryUuid, String word, String translation) {
        this.dictionaryUuid = dictionaryUuid;
        this.word = word;
        this.translation = translation;
    }
}
