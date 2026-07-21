package com.example.mspassportinspectorgateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleCheckRequestDto {
    @JsonProperty("extId")
    @NotBlank(message = "{singleCheck.extId.notBlank}")
    @Size(max = 64, message = "{singleCheck.extId.size}")
    private String extId;
    @JsonProperty("personLastName")
    @NotBlank(message = "{singleCheck.personLastName.notBlank}")
    @Size(max = 100, message = "{singleCheck.personLastName.size}")
    private String personLastName;
    @JsonProperty("personFirstName")
    @NotBlank(message = "{singleCheck.personFirstName.notBlank}")
    @Size(max = 100, message = "{singleCheck.personFirstName.size}")
    private String personFirstName;
    @JsonProperty("personMiddleName")
    @NotBlank(message = "{singleCheck.personMiddleName.notBlank}")
    @Size(max = 100, message = "{singleCheck.personMiddleName.size}")
    private String personMiddleName;
    @JsonProperty("docSeriesNo")
    @NotBlank(message = "{singleCheck.docSeriesNo.notBlank}")
    @Pattern(regexp = "\\d{3,4}", message = "{singleCheck.docSeriesNo.pattern}")
    private String docSeriesNo;
    @JsonProperty("docNo")
    @NotBlank(message = "{singleCheck.docNo.notBlank}")
    @Pattern(regexp = "\\d{6,10}", message = "{singleCheck.docNo.pattern}")
    private String docNo;
}
