package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.services.DictionaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dictionaries")
@RequiredArgsConstructor
@Slf4j
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @GetMapping("/{dictionaryUuid}")
    @Operation(summary = "Get dictionary by uuid", tags = "dictionaries")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Requested dictionary",
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
    public ResponseEntity<?> findDictionaryByUuid(@PathVariable UUID dictionaryUuid) {
        log.debug("received get dictionary by uuid request");
        return ResponseEntity.ok(
                dictionaryService.findDictionaryByUuid(dictionaryUuid)
        );
    }

    @GetMapping
    @Operation(summary = "Get all dictionaries", tags = "dictionaries")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All dictionaries list",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<?> findAllDictionaries() {
        log.debug("received get all dictionaries request");
        return ResponseEntity.ok(
                dictionaryService.findAllDictionaries()
        );
    }
}
