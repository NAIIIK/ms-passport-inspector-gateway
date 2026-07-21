package com.example.mspassportinspectorgateway.model;

import com.example.mspassportinspectorgateway.model.type.CheckStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleCheckResultDto {
    private CheckStatus checkStatus;
    private String extId;
}
