package com.example.mspassportinspectorgateway.service;

import com.example.mspassportinspectorgateway.client.PassportInspectorClient;
import com.example.mspassportinspectorgateway.model.BatchCheckResultDto;
import com.example.mspassportinspectorgateway.model.CheckInitResponseDto;
import com.example.mspassportinspectorgateway.model.SingleCheckRequestDto;
import com.example.mspassportinspectorgateway.model.SingleCheckResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PassportCheckBffService {

    private final PassportInspectorClient passportInspectorClient;

    public CheckInitResponseDto initSingleCheck(SingleCheckRequestDto request) {
        return passportInspectorClient.initSingleCheck(request);
    }

    public SingleCheckResultDto getSingleCheckResult(UUID jobId) {
        return passportInspectorClient.getSingleCheckResult(jobId);
    }

    public CheckInitResponseDto initBatchCheck(MultipartFile file) {
        return passportInspectorClient.initBatchCheck(file);
    }

    public BatchCheckResultDto getBatchCheckResult(UUID jobId) {
        return passportInspectorClient.getBatchCheckResult(jobId);
    }
}
