package org.vescm.yerbamateapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.vescm.yerbamateapi.dto.request.YerbaMateRequest;
import org.vescm.yerbamateapi.dto.response.YerbaMateResponse;
import org.vescm.yerbamateapi.model.YerbaMate;

@Mapper
public interface YerbaMateMapper {
    YerbaMateMapper INSTANCE = Mappers.getMapper(YerbaMateMapper.class);

    YerbaMate fromResponseToModel(YerbaMateResponse YerbaMateResponse);
    YerbaMate fromResquestToModel(YerbaMateRequest yerbaMateRequest);
    YerbaMateResponse fromModelToResponseDto(YerbaMate yerbaMate);
    YerbaMateRequest fromModelToRequestDto(YerbaMate yerbaMate);
    YerbaMateResponse fromResponseToRequestDto(YerbaMateRequest yerbaMateRequest);
    YerbaMateRequest fromResponseToRequestDto(YerbaMateResponse yerbaMateResponse);
}
