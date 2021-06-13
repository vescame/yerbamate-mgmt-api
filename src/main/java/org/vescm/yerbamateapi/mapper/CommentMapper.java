package org.vescm.yerbamateapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.vescm.yerbamateapi.dto.request.CommentRequest;
import org.vescm.yerbamateapi.dto.response.CommentResponse;
import org.vescm.yerbamateapi.model.Comment;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment fromResponseToModel(CommentResponse commentResponse);
    Comment fromRequestToModel(CommentRequest commentRequest);
    CommentResponse fromModelToResponseDto(Comment comment);
    CommentRequest fromModelToRequestDto(Comment comment);
    CommentResponse fromRequestToResponseDto(CommentRequest commentRequest);
    CommentRequest fromResponseToRequestDto(CommentResponse commentResponse);
}
