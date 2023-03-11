package com.censored.blooperapp.controller;

import com.censored.blooperapp.dto.SensitiveWordDto;
import com.censored.blooperapp.service.BleepService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bleep")
@RequiredArgsConstructor
public class BleepController {

    private final BleepService bleepService;

    @PostMapping
    @Operation(
            summary = "Bleep words",
            description = "Bleep out all the sensitive words from the sentence"
    )
    public ResponseEntity<?> bleepWord(@RequestParam(name = "sentence") String sentence) {
        return ResponseEntity.ok(bleepService.bleepWords(sentence));
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all Words",
            description = "Retrieve all words from table"
    )
    public ResponseEntity<?> getAllWords() {
        return ResponseEntity.ok(bleepService.getAllBleeps());
    }


    @PostMapping("/add")
    @Operation(
            summary = "Add Word",
            description = "Add word to the table"
    )
    public ResponseEntity<?> addNewWords(@RequestBody SensitiveWordDto sensitiveWordDto) {
        bleepService.addBleep(sensitiveWordDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{sensitiveWordId}")
    @Operation(
            summary = "Delete Word",
            description = "Delete the word from the table by id"
    )
    public ResponseEntity<?> deleteWord(@PathVariable Long sensitiveWordId) {
        bleepService.deleteWord(sensitiveWordId);
        return ResponseEntity.ok().build();
    }


}
