package org.vescm.yerbamateapi.builder;

import lombok.Builder;
import org.vescm.yerbamateapi.dto.request.CommentRequest;
import org.vescm.yerbamateapi.dto.request.YerbaMateRequest;
import org.vescm.yerbamateapi.dto.response.CommentResponse;
import org.vescm.yerbamateapi.dto.response.YerbaMateResponse;
import org.vescm.yerbamateapi.enums.YerbaMateType;
import org.vescm.yerbamateapi.model.Comment;
import org.vescm.yerbamateapi.model.YerbaMate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public class YerbaMateBuilder {
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Argentinian Yerba";

    @Builder.Default
    private YerbaMateType yerbaMateType = YerbaMateType.PARAGUAYAN;

    @Builder.Default
    private Double price = 18.50D;

    @Builder.Default
    private List<CommentRequest> comments =
            Collections.singletonList(new CommentRequest("Dummy Comment"));

    public YerbaMateRequest toRequestDto() {
        return new YerbaMateRequest(name, yerbaMateType, price, comments);
    }

    public YerbaMate toEntity() {
        List<Comment> commentEntities = this.comments
                .stream()
                .map(c -> Comment.builder().comment(c.getComment()).build())
                .collect(Collectors.toList());
        return new YerbaMate(id, name, yerbaMateType, price, commentEntities);
    }

    public YerbaMateResponse toResponseDto() {
        List<CommentResponse> commentResponses = comments
                .stream()
                .map(c -> new CommentResponse(c.getComment()))
                .collect(Collectors.toList());
        return new YerbaMateResponse(id, name, yerbaMateType, price, commentResponses);
    }
}
