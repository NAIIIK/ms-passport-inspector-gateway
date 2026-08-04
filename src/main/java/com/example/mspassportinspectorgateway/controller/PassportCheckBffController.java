package com.example.mspassportinspectorgateway.controller;

import com.example.mspassportinspectorgateway.model.BatchCheckResultDto;
import com.example.mspassportinspectorgateway.model.CheckInitResponseDto;
import com.example.mspassportinspectorgateway.model.SingleCheckRequestDto;
import com.example.mspassportinspectorgateway.model.SingleCheckResultDto;
import com.example.mspassportinspectorgateway.service.PassportCheckBffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PassportCheckBffController {
    private final PassportCheckBffService passportCheckBffService;

    @PostMapping(
            value = "/passport-checks",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CheckInitResponseDto> initSingleCheck(@RequestBody @Valid SingleCheckRequestDto request) {
        return ResponseEntity.ok(passportCheckBffService.initSingleCheck(request));
    }

    @GetMapping(
            value = "/passport-checks/{jobId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SingleCheckResultDto> getSingleCheckResult(@PathVariable UUID jobId) {
        return ResponseEntity.ok(passportCheckBffService.getSingleCheckResult(jobId));
    }

    @PostMapping(
            value = "/passport-check-batches",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CheckInitResponseDto> initBatchCheck(@RequestParam("inputFile") MultipartFile file) {
        return ResponseEntity.ok(passportCheckBffService.initBatchCheck(file));
    }

    @GetMapping(
            value = "/passport-check-batches/{jobId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BatchCheckResultDto> getBatchCheckResult(@PathVariable UUID jobId) {
        return ResponseEntity.ok(passportCheckBffService.getBatchCheckResult(jobId));
    }
}
