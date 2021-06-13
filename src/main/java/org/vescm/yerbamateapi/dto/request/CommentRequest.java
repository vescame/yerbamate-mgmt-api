package org.vescm.yerbamateapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 255)
    private String comment;
}
