package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtos.word.CreateWordDto;
import org.example.dtos.word.UpdateWordDto;
import org.example.services.WordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
@RequiredArgsConstructor
@Slf4j
public class WordController {
    private final WordService wordService;

    @GetMapping("/{dictionaryUuid}/words")
    @Operation(summary = "Get word(s) by dictionary uuid and word", tags = "words")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Requested word(s)",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dictionary not found",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<?> findWordsByDictionaryUuidAndWord(@PathVariable UUID dictionaryUuid, @RequestParam(required = false) String word) {
        log.debug("received get word(s) by dictionary uuid and word request");
        return ResponseEntity.ok(
                wordService
                        .findWordsByDictionaryUuidAndWord(dictionaryUuid, word)
        );
    }

    @GetMapping("/words")
    @Operation(summary = "Find word in all dictionaries", tags = "words")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Requested word(s)",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Word not found",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<?> findWordsByWord(@RequestParam String word) {
        log.debug("received get word(s) by word request");
        return ResponseEntity.ok(
                wordService
                        .findWordsByWord(word)
        );
    }

    @PostMapping("/{dictionaryUuid}/words")
    @Operation(summary = "Add word to dictionary", tags = "words")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Word created",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Word not valid",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dictionary not found",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<?> addWordToDictionary(@PathVariable UUID dictionaryUuid, @RequestBody CreateWordDto createWordDto) {
        log.debug("received add word request");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        wordService.addWordToDictionary(
                                dictionaryUuid, createWordDto
                        )
                );
    }

    @PutMapping("/{dictionaryUuid}/words/{wordUuid}")
    @Operation(summary = "Update word", tags = "words")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Word updated",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "Word not valid",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dictionary not found",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<?> updateWord(@PathVariable UUID dictionaryUuid, @PathVariable UUID wordUuid, @RequestBody UpdateWordDto updateWordDto) {
        log.debug("received update word request");
        return ResponseEntity.ok(
                wordService.updateWord(
                        dictionaryUuid, wordUuid, updateWordDto
                )
        );
    }

    @DeleteMapping("/{dictionaryUuid}/words")
    @Operation(summary = "Delete word from dictionary", tags = "words")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Word deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dictionary not found",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<?> deleteWordByDictionaryUuidAndWord(@PathVariable UUID dictionaryUuid, @RequestParam String word) {
        log.debug("received delete word request");
        wordService.deleteWordByDictionaryUuidAndWord(
                dictionaryUuid, word
        );
        return ResponseEntity
                .noContent()
                .build();
    }
}
