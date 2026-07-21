package com.example.mspassportinspectorgateway.client;

import com.example.mspassportinspectorgateway.config.PassportInspectorFeignConfig;
import com.example.mspassportinspectorgateway.model.BatchCheckResultDto;
import com.example.mspassportinspectorgateway.model.CheckInitResponseDto;
import com.example.mspassportinspectorgateway.model.SingleCheckRequestDto;
import com.example.mspassportinspectorgateway.model.SingleCheckResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@FeignClient(
        name = "passport-inspector-client",
        url = "${passport-inspector.client.url}",
        configuration = PassportInspectorFeignConfig.class
)
public interface PassportInspectorClient {

    @PostMapping(
            value = "/v1/internal/validation/smev/4/clients/check",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CheckInitResponseDto initSingleCheck(@RequestBody SingleCheckRequestDto request);

    @GetMapping(
            value = "/v1/internal/validation/smev/4/clients/check/{jobId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    SingleCheckResultDto getSingleCheckResult(@PathVariable("jobId") UUID jobId);

    @PostMapping(
            value = "/v1/internal/validation/smev/4/clients/check/batch",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CheckInitResponseDto initBatchCheck(@RequestPart("inputFile") MultipartFile file);

    @GetMapping(
            value = "/v1/internal/validation/smev/4/clients/check/batch/{jobId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    BatchCheckResultDto getBatchCheckResult(@PathVariable("jobId") UUID jobId);

}
