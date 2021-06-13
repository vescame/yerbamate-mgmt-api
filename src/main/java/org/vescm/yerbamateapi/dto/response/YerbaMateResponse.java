package org.vescm.yerbamateapi.dto.response;

import org.vescm.yerbamateapi.model.YerbaMateType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YerbaMateResponse {
    private Long id;
    private String name;
    private YerbaMateType yerbaMateType;
    private Double price;
    private List<CommentResponse> comments;
}
