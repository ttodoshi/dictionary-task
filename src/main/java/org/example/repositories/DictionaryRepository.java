package org.example.repositories;

import org.example.models.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DictionaryRepository extends JpaRepository<Dictionary, UUID> {
}
