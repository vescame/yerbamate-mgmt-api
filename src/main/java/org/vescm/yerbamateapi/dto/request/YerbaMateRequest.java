package org.vescm.yerbamateapi.dto.request;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.vescm.yerbamateapi.enums.YerbaMateType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YerbaMateRequest {
    @NotBlank
    @Size(max = 45)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private YerbaMateType yerbaMateType;

    @DecimalMin(value = "6.0")
    private Double price;

    @Size(min = 1, max = 255)
    private List<CommentRequest> comments;
}
