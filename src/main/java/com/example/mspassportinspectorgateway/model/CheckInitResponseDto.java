package com.example.mspassportinspectorgateway.model;

import com.example.mspassportinspectorgateway.model.type.CheckStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckInitResponseDto {
    private UUID jobId;
    private CheckStatus checkStatus;
    private String errorCause;
}
